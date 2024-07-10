import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import {CartService} from "../service/cart.service";

@Component({
  selector: 'app-header-cart',
  templateUrl: './header-cart.component.html',
})
export class HeaderCartComponent implements OnInit, OnDestroy {
  totalCartItems: number
  totalCartPrice: number

  private cardSubscription: Subscription;

  constructor(private cartService: CartService) {}

  ngOnInit() {
    this.cartService.getCartState().subscribe(cartState => {
      if (cartState) {
        this.totalCartItems = cartState.totalItems;
        this.totalCartPrice = cartState.totalPrice;
      }
    });
  }

  ngOnDestroy() {
    if (this.cardSubscription) {
      this.cardSubscription.unsubscribe();
    }
  }
}
