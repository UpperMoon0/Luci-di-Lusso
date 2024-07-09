import {Component, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";

@Component({
  selector: 'app-home-product-row',
  templateUrl: './home-product-row.component.html',
  styleUrls: ['./home-product-row.component.css',]
})
export class HomeProductRowComponent implements OnInit {
  products: any[] = [];

  constructor(private productService: ProductService) {

  }

  ngOnInit(): void {
    const types = [];
    const minPrice = 0;
    const maxPrice = 0;
    const request = {types, minPrice, maxPrice};
    this.productService.getJewelries(request).subscribe(res => {
      this.products = res.jewelries;
    })
  }
}
