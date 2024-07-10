import { Component, ViewChild, OnInit } from '@angular/core';
import { StripeService, StripeCardComponent } from 'ngx-stripe';
import { PaymentService } from "../service/payment.service";
import { CartService } from "../service/cart.service";
import { Subscription } from 'rxjs';
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html',
  styleUrls: ['./payment-form.component.css']
})
export class PaymentFormComponent implements OnInit {
  @ViewChild(StripeCardComponent) card: StripeCardComponent;
  customerName: string = '';
  totalPrice: number;
  private cartSubscription: Subscription;

  constructor(
    private stripeService: StripeService,
    private paymentService: PaymentService,
    private cartService: CartService,
    private toastrService: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    // Subscribe to cartState to get totalPrice
    this.cartSubscription = this.cartService.getCartState().subscribe(cartState => {
      this.totalPrice = cartState.totalPrice;
    });
  }

  ngOnDestroy() {
    // Ensure the subscription is properly unsubscribed when the component is destroyed
    if (this.cartSubscription) {
      this.cartSubscription.unsubscribe();
    }
  }

  buy() {
    if (this.card.element) {
      this.stripeService
        .createToken(this.card.element, { name: this.customerName })
        .subscribe(result => {
          if (result.token) {
            this.paymentService.createCharge(result.token.id)
              .subscribe(res => {
                this.toastrService.success("Payment successful!");
                this.router.navigate(['/home']).then(r => {});
              });
          } else if (result.error) {
            this.toastrService.error("Payment failed!");
          }
        });
    }
  }
}
