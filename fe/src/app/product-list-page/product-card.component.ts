import { Component, Input, OnInit } from '@angular/core';
import { CartService } from '../service/cart.service'; // Adjust the import path as necessary

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {
  @Input() product: any;

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
  }

  convertNumber(price: number): string {
    return `$${price.toFixed(2)}`;
  }
}
