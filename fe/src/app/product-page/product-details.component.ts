import { Component, OnInit } from '@angular/core';
import {ProductService} from "../service/product-service";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  product: any;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getJewelry(2);
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
