import { Component, Input } from '@angular/core';
import { CartService } from '../service/cart.service';

@Component({
  selector: 'app-cart-product-card',
  templateUrl: './cart-product-card.component.html',
  styleUrls: ['./cart-product-card.component.css']
})
export class CartProductCardComponent {
  @Input() product: { id: number, imageUrl: string; name: string; size: string, price: number, quantity: number; createAt: string; } = {
    id: 0,
    imageUrl: '',
    name: '',
    price: 0,
    size: '',
    quantity: 0,
    createAt: ''
  };

  constructor(private cartService: CartService) { }

  decreaseQuantity() {
    if (this.product.quantity > 1) {
      this.product.quantity -= 1;
      this.updateQuantity();
    }
  }

  increaseQuantity() {
    this.product.quantity += 1;
    this.updateQuantity();
  }

  updateQuantity() {
    this.cartService.updateQuantity(this.product.id, this.product.quantity).subscribe({
      next: (res) => {
        console.log(res);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  remove() {
    this.cartService.deleteItem(this.product.id).subscribe({
      next: (res) => {
        console.log(res);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }
}
