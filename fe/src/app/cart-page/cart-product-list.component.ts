import { Component, OnInit } from '@angular/core';
import { CartService } from '../service/cart.service';

@Component({
  selector: 'app-cart-product-list',
  templateUrl: './cart-product-list.component.html',
  styleUrls: ['./cart-product-list.component.css']
})
export class CartProductListComponent implements OnInit {
  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.cartService.getCartItems();
  }

  get products() {
    return this.cartService.cartItemList;
  }

  get totalPrice() {
    return this.cartService.totalPrice;
  }
}
