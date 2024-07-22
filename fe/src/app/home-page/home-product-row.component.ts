import {Component, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {ColorService} from "../service/color.service";

@Component({
  selector: 'app-home-product-row',
  templateUrl: './home-product-row.component.html',
  styleUrls: ['./home-product-row.component.css',]
})
export class HomeProductRowComponent implements OnInit {
  products: any[] = [];

  constructor(private productService: ProductService,
              protected colorService: ColorService) {
  }

  ngOnInit(): void {
    let request = {types: [], minPrice: 0, maxPrice: 0, keyword: ''};
    this.productService.getJewelries(request).subscribe(res => {
      this.products = res.jewelries;
    })
  }
}
