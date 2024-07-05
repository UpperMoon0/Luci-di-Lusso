import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-cart-product-card',
  templateUrl: './cart-product-card.component.html',
  styleUrls: ['./cart-product-card.component.css']
})
export class CartProductCardComponent {
  @Input() product: { imageUrl: string; name: string; price: number; quantity: number; createAt: string; } = {
    imageUrl: '',
    name: '',
    price: 0,
    quantity: 0,
    createAt: ''
  };
}
