import { Component, OnInit } from '@angular/core';
import {ProductService} from "../service/product-service";
import {AccountService} from "../service/account-service";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  product: any;
  isLoggedIn: boolean;

  constructor(private productService: ProductService,
              private accountService: AccountService) { }

  ngOnInit(): void {
    this.getJewelry(2);
    this.accountService.checkToken();
    this.accountService.isLoggedIn.subscribe(value => {
      this.isLoggedIn = value;
    });
  }

  getJewelry(id: number) {
    this.productService.getJewelry(id).subscribe({
      next: (res) => {
        this.product = res;
      },
      error: (err) => {
        console.log("Error: ", err);
      }
    });
  }

  addToCart(product: any) {
    console.log("Product added to cart: ", product);
  }
}
