import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-cart-product-card',
  templateUrl: './cart-product-card.component.html',
  styleUrls: ['./cart-product-card.component.css']
})
export class CartProductCardComponent {
  @Input() product: { imageUrl: string; name: string; size: string, price: number, quantity: number; createAt: string; } = {
    imageUrl: '',
    name: '',
    price: 0,
    size: '',
    quantity: 0,
    createAt: ''
  };

  decreaseQuantity(product: any) {
    // Decrease the quantity of the product
    if (product.quantity > 1) {
      product.quantity -= 1;
      // Update the cart service or local storage as needed
    }
  }

  increaseQuantity(product: any) {
    // Increase the quantity of the product
    product.quantity += 1;
    // Update the cart service or local storage as needed
  }

  remove(product: any) {
    // Remove the product from the cart
    // Update the cart service or local storage as needed
  }
}
