import { Component, ViewChild, OnInit } from '@angular/core';
import { StripeService, StripeCardComponent } from 'ngx-stripe';
import { PaymentService } from "../service/payment.service";
import { CartService } from "../service/cart.service";

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html',
  styleUrls: ['./payment-form.component.css']
})
export class PaymentFormComponent implements OnInit {
  @ViewChild(StripeCardComponent) card: StripeCardComponent;
  customerName: string = '';
  totalPrice: number;

  constructor(
    private stripeService: StripeService,
    private paymentService: PaymentService,
    private cartService: CartService
  ) { }

  ngOnInit() {
    this.cartService.totalPrice.subscribe(price => {
      this.totalPrice = price;
    });
  }

  buy() {
    console.log(this.customerName);
    this.stripeService
      .createToken(this.card.getCard(), { name: this.customerName })
      .subscribe(result => {
        if (result.token) {
          console.log(result.token.id);
          this.paymentService.createCharge(result.token.id, this.totalPrice, this.customerName)
            .subscribe(res => {
              console.log(res);
            });
        } else if (result.error) {
          console.log(result.error.message);
        }
      });
  }
}
