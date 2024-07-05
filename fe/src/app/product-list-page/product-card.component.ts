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

  addToCart(): void {
    this.cartService.addToCart(this.product.id).subscribe({
      next: (response) => {
        console.log('Product added to cart:', response);
      },
      error: (error) => {
        console.error('Error adding product to cart:', error);
      }
    });
  }
}
