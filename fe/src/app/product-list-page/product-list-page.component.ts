import { Component, OnInit } from '@angular/core';
import { ToastrService } from "ngx-toastr";
import { ProductService } from "../service/product.service";
import { CartService } from "../service/cart.service";
import {ColorService} from "../service/color.service";

@Component({
  selector: 'app-product-list-page-page',
  templateUrl: './product-list-page.component.html',
  styleUrls: ['./product-list-page.component.css']
})
export class ProductListPageComponent implements OnInit {
  public typeLists: string[] = [];
  public priceRange: string[] = ["$ 0 - 100", "$ 100 - 500", "$ 500 - 1000", "$ 1000 - 4000", "More than $ 4000"];
  public typeColors: Map<string, [string, string]> = new Map();

  isLoggedIn: boolean = false;
  selectedRangePrice: string = "";
  productsList: any[] = [];
  selectedTypes: string[] = [];

  constructor(private productService: ProductService,
              private toastrService: ToastrService,
              protected colorService: ColorService) {}

  ngOnInit(): void {
    this.isLoggedIn = localStorage.getItem("user") != null;
    // Subscribe to productTypes observable
    this.productService.getProductTypes().subscribe({
      next: (types: string[]) => {
        this.typeLists = types;
        this.getProducts();
      },
      error: (error) => this.toastrService.error(),
      complete: () => console.log('Completed fetching tags')
    });
    this.getProducts();
  }

  public getSelectedTypes(tag: string): void {
    const index = this.selectedTypes.indexOf(tag.toUpperCase());
    const isSelected = index > -1;
    this.toggleSelectionType(isSelected, tag, this.selectedTypes, index);
    this.getProducts();
  }

  public getSelectedPriceRange(price: string): void {
    const isSelected = this.selectedRangePrice === price;
    this.toggleSelectionPrice(isSelected, price);
    this.getProducts();
  }

  private toggleSelectionType(isSelected: boolean, value: string, array: string[], index: number): void {
    const element = document.getElementById(value);
    if (isSelected) {
      array.splice(index, 1);
      element.style.backgroundColor = "white";
      element.style.color = "black";
      element.style.fontWeight = "normal";
    } else {
      array.push(value.toUpperCase());
      element.style.backgroundColor = "#173334FF";
      element.style.color = "white";
      element.style.fontWeight = "bold";
    }
  }

  private toggleSelectionPrice(isSelected: boolean, value: string): void {
    const element = document.getElementById(value);
    if (isSelected) {
      this.selectedRangePrice = "";
      element.style.cssText = "font-weight: normal; background-color: white; color: black;";
    } else {
      //Remove style from the last Price
      if (this.selectedRangePrice) {
        const prevElement = document.getElementById(this.selectedRangePrice);
        if (prevElement) {
          prevElement.style.cssText = "font-weight: normal; background-color: white; color: black;";
        }
      }
      this.selectedRangePrice = value;
      element.style.cssText = "width: 100%; font-weight: bold; background-color: #173334FF; color: white;";
    }
  }

  getProducts(): void {
    const types = this.selectedTypes.map(tag => tag.includes(" ") ? tag.replace(" ", "_").toUpperCase() : tag.toUpperCase());
    const { minPrice, maxPrice } = this.getPriceRange();
    const request = { types, minPrice, maxPrice };
    this.productService.getJewelries(request).subscribe({
      next: (res) => this.productsList = res.jewelries,
      error: () => this.toastrService.error("Error in getting products list")
    });
  }

  private getPriceRange(): { minPrice: number; maxPrice: number } {
    const range = this.selectedRangePrice.split("-").map(part => parseInt(part.replace(/[^0-9]/g, '').trim()));
    return { minPrice: range[0] || 0, maxPrice: range[1] || 0 };
  }
}
