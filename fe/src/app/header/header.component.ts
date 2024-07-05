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
  private totalPriceSubscription: Subscription;

  constructor(private accountService: AccountService,
              private cartService: CartService) {}

  ngOnInit() {
    this.accountService.checkToken();
    this.accountService.isLoggedIn.subscribe(value => {
      this.isLoggedIn = value;
    });

    // Subscribe to totalPrice from CartService
    this.totalPriceSubscription = this.cartService.totalPrice.subscribe(newTotalPrice => {
      this.totalCartPrice = newTotalPrice;
    });
  }

  ngOnDestroy() {
    // Unsubscribe to prevent memory leaks
    if (this.totalPriceSubscription) {
      this.totalPriceSubscription.unsubscribe();
    }
  }

  logOut() {
    this.accountService.logout();
  }
}
