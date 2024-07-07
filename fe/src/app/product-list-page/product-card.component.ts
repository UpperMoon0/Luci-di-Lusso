import { Component, Input, OnInit } from '@angular/core';
import { CartService } from '../service/cart.service';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {
  @Input() product: any;
  @Input() typeColors: string[];

  constructor() { }

  ngOnInit(): void {
  }

  convertNumber(price: number): string {
    return `$${price.toFixed(2)}`;
  }

  // Function to get the gradient string for the product type
  getTypeGradient(type: string): string {
    return `linear-gradient(45deg, ${this.typeColors[0]}, ${this.typeColors[1]})`;
  }
}
