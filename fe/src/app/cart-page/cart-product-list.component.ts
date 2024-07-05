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

  // Helper getter to simplify template access
  get products() {
    return this.cartService.cartItemList;
  }
}
