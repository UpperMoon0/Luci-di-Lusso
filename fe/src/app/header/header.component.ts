import { Component, OnInit, OnDestroy } from '@angular/core';
import { AccountService } from "../service/account.service";
import { CartService } from "../service/cart.service";
import { Subscription } from "rxjs";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit, OnDestroy {
  isLoggedIn: boolean = false;
  totalCartPrice: number = 0;
  totalItems: number = 0;
  private cartSubscription: Subscription;

  constructor(private accountService: AccountService,
              private cartService: CartService) {}

  ngOnInit() {
    this.accountService.checkToken();
    this.accountService.isLoggedIn.subscribe(value => {
      this.isLoggedIn = value;
    });

    this.cartSubscription = this.cartService.getCartState().subscribe(cartState => {
      this.totalCartPrice = cartState.totalPrice;
      this.totalItems = cartState.totalItems;
    });
  }

  ngOnDestroy() {
    // Ensure the subscription is properly unsubscribed when the component is destroyed
    if (this.cartSubscription) {
      this.cartSubscription.unsubscribe();
    }
  }
}
