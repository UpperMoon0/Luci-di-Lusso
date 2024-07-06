import { Component, OnInit } from '@angular/core';
import { CartService } from '../service/cart.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart-product-list',
  templateUrl: './cart-product-list.component.html',
  styleUrls: ['./cart-product-list.component.css']
})
export class CartProductListComponent implements OnInit {
  constructor(private cartService: CartService, private router: Router) { }

  ngOnInit(): void {
    this.cartService.getCartItems();
  }

  get products() {
    return this.cartService.cartItemList;
  }

  get totalPrice() {
    return this.cartService.totalPrice;
  }

  clearCart() {
    this.cartService.clearCart().subscribe({
      next: (res) => {
        console.log(res);
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  proceedToCheckout() {
    this.router.navigate(['/payment']).then(r => "");
  }
}
