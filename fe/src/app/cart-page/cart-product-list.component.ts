import { Component, OnInit, OnDestroy } from '@angular/core';
import { CartService } from '../service/cart.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-cart-product-list',
  templateUrl: './cart-product-list.component.html',
  styleUrls: ['./cart-product-list.component.css']
})
export class CartProductListComponent implements OnInit, OnDestroy {
  products = [];
  totalPrice = 0;
  totalItems = 0;
  private cartSubscription: Subscription;

  constructor(private cartService: CartService, private router: Router) { }

  ngOnInit(): void {
    this.cartSubscription = this.cartService.getCartState().subscribe(cartState => {
      this.products = cartState.cartItems;
      this.totalPrice = cartState.totalPrice;
      this.totalItems = cartState.totalItems;
    });
  }

  ngOnDestroy(): void {
    if (this.cartSubscription) {
      this.cartSubscription.unsubscribe();
    }
  }

  clearCart() {
    this.cartService.clearCart().subscribe({
      next: (res) => {
        console.log(res);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  proceedToCheckout() {
    this.router.navigate(['/payment']).then(r => "");
  }
}
