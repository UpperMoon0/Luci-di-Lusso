import { Component, OnInit } from '@angular/core';
import { ToastrService } from "ngx-toastr";
import { ProductService } from "../service/product.service";
import { CartService } from "../service/cart.service";
import { NumberService } from "../modules/service/number.service";

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

  constructor(private productService: ProductService, private toastrService: ToastrService, private numberFormat: NumberService, private cartService: CartService) {}

  ngOnInit(): void {
    this.isLoggedIn = localStorage.getItem("user") != null;
    this.getAllTypes();
    this.getProducts();
  }

  private getAllTypes(): void {
    this.productService.getAllTypes().subscribe({
      next: (res: string[]) => {
        this.typeLists = res;
        this.typeColors = mapTypesToColors(this.typeLists);
      },
      error: (error) => this.toastrService.error(),
      complete: () => console.log('Completed fetching tags')
    });
  }

  public getSelectedTypes(tag: string): void {
    const index = this.selectedTypes.indexOf(tag.toUpperCase());
    const isSelected = index > -1;
    this.toggleSelection(isSelected, tag, this.selectedTypes, index);
    this.getProducts();
  }

  public getSelectedPriceRange(price: string): void {
    const isSelected = this.selectedRangePrice === price;
    this.toggleSelection(isSelected, price, this.priceRange);
    this.getProducts();
  }

  private toggleSelection(isSelected: boolean, value: string, array: string[], index?: number): void {
    const element = document.getElementById(value);
    if (isSelected) {
      if (index !== undefined) array.splice(index, 1);
      else this.selectedRangePrice = "";
      element.style.cssText = "font-weight: normal; background-color: white; color: black;";
    } else {
      if (array === this.selectedTypes) array.push(value.toUpperCase());
      else {
        // Deselect the previously selected price range
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
  }

  getProducts(): void {
    const types = this.selectedTypes.map(tag => tag.includes(" ") ? tag.replace(" ", "_").toUpperCase() : tag.toUpperCase());
    const { minPrice, maxPrice } = this.getPriceRange();
    const request = { types, minPrice, maxPrice };
    this.productService.getJewelries(request).subscribe({
      next: (res) => this.productsList = res.jewelries,
      error: () => this.toastrService.error()
    });
  }

  private getPriceRange(): { minPrice: number; maxPrice: number } {
    const range = this.selectedRangePrice.split("-").map(part => parseInt(part.replace(/[^0-9]/g, '').trim()));
    return { minPrice: range[0] || 0, maxPrice: range[1] || 0 };
  }

  getTypeGradient(type: string): [string, string] {
    const colors = this.typeColors.get(type);
    if (colors) {
      return colors;
    } else {
      // Default color pair if the type is not found
      return ['rgba(0, 0, 0, 0.7)', 'rgba(255, 255, 255, 0.7)'];
    }
  }
}

function generateVibrantColors(index: number): [string, string] {
  const baseHue = (index * 137) % 360; // Using the golden angle approximation for distribution
  const color1 = `hsl(${baseHue}, 70%, 55%)`;
  const color2 = `hsl(${(baseHue + 45) % 360}, 70%, 55%)`; // Offset by 45 degrees for the second color
  return [color1, color2];
}

function mapTypesToColors(typeLists: string[]): Map<string, [string, string]> {
  const typeColors = new Map<string, [string, string]>();
  typeLists.forEach((type, index) => {
    typeColors.set(type, generateVibrantColors(index));
  });
  return typeColors;
}

