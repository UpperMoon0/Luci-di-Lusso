import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {ProductService} from "../service/product.service";
import {CartService} from "../service/cart.service";
import {NumberService} from "../modules/service/number.service";

@Component({
  selector: 'app-product-list-page-page',
  templateUrl: './product-list-page.component.html'
})
export class ProductListPageComponent {

  selectedRangePrice: string = "";
  productsList: any[] = [];
  selectedTags: string[] = [];
  public tagsList: string[] = [];
  isLoggedIn: boolean = false;

  public priceRange: string[] = [
    "$ 0 - 100",
    "$ 100 - 500",
    "$ 500 - 1000",
    "$ 1000 - 4000",
    "More than $ 4000"
  ];

  constructor(private productService: ProductService,
              private toastrService: ToastrService,
              private numberFormat: NumberService,
              private cartService: CartService) {
  }

  ngOnInit(): void {
    this.isLoggedIn = localStorage.getItem("user") != null;
    this.getAllTags();
    this.getProducts();
  }

  private getAllTags(): void {
    this.productService.getAllTypes().subscribe({
      next: (res: string[]) => {
        this.tagsList = res;
      },
      error: (error) => {
        this.toastrService.error(error.message);
      },
      complete: () => {
        console.log('Completed fetching tags');
      }
    });
  }

  //Get Products after selecting tags
  public getSelectedTags(tag : string) {
    if (this.selectedTags.includes(tag.toUpperCase())) {
      for (let i = 0; i < this.selectedTags.length; i++) {
        if (this.selectedTags[i] == tag.toUpperCase()) {
          this.selectedTags.splice(i, 1);
        }
      }
      const id = document.getElementById(tag);
      id.style.fontWeight = "normal";
      id.style.backgroundColor = "white";
      id.style.color = "black";
    } else {
      this.selectedTags.push(tag.toUpperCase());
      const id = document.getElementById(tag);
      id.style.width = "100%";
      id.style.fontWeight = "bold";
      id.style.backgroundColor = "#173334FF";
      id.style.color = "white";
    }
    this.getProducts();
  }

  //Get Products after selecting price
  public getSelectedPriceRange(price : string) {
    if (this.selectedRangePrice.includes(price)) {
      this.selectedRangePrice = "";
      for (let i = 0; i < this.priceRange.length; i++) {
        let temp = this.priceRange[i];
        const id = document.getElementById(temp);
        id.style.fontWeight = "normal";
        id.style.backgroundColor = "white";
        id.style.color = "black";
      }
    } else {
      this.selectedRangePrice = price;
      const id = document.getElementById(price);
      id.style.width = "100%";
      id.style.fontWeight = "bold";
      id.style.backgroundColor = "#173334FF";
      id.style.color = "white";
      for (let i = 0; i < this.priceRange.length; i++) {
        if (this.priceRange[i] != price) {
          let temp = this.priceRange[i];
          const id = document.getElementById(temp);
          id.style.fontWeight = "normal";
          id.style.backgroundColor = "white";
          id.style.color = "black";
        }
      }
    }
    this.getProducts();
  }

  convertNumber(number){
    return this.numberFormat.convertNumber(number);
  }

  getProducts() {
    for (let i = 0; i < this.selectedTags.length; i++) {
      if (this.selectedTags[i].includes(" ")) {
        this.selectedTags[i] = this.selectedTags[i].split(" ")[0] + "_" + this.selectedTags[i].split(" ")[1];
      }
      this.selectedTags[i].toUpperCase();
    }
    let types: String[] = this.selectedTags;
    let minPrice: number = 0;
    let maxPrice: number = 0;
    switch (this.selectedRangePrice) {
      case "$ 0 - 100":
        minPrice = 0;
        maxPrice = 100;
        break;
      case "$ 100 - 500":
        minPrice = 100;
        maxPrice = 500;
        break;
      case "$ 500 - 1000":
        minPrice = 500;
        maxPrice = 1000;
        break;
      case "$ 1000 - 4000":
        minPrice = 1000;
        maxPrice = 4000;
        break;
      case "More than $ 4000":
        minPrice = 4000;
        maxPrice = 0;
        break;
      default:
        minPrice = 0;
        maxPrice = 0;
    }
    const request = {
      types : types,
      minPrice : minPrice,
      maxPrice : maxPrice
    } as any;
    this.productService.getJewelries(request).subscribe({
      next: (res) => {
        this.productsList = res.jewelries;
      },
      error: () => {
        this.toastrService.error("Error in getting products list");
      }
    });
  }
}
