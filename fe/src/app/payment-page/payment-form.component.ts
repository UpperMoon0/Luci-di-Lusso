import {Component, ViewChild, OnInit, OnDestroy, inject} from '@angular/core';
import { StripeService, StripeCardComponent } from 'ngx-stripe';
import { PaymentService } from "../service/payment.service";
import { CartService } from "../service/cart.service";
import { Subscription } from 'rxjs';
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {ManagerService} from "../service/manager.service";
import {VoucherService} from "../service/voucher.service";

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html',
  styleUrls: ['./payment-form.component.css']
})
export class PaymentFormComponent implements OnInit, OnDestroy {
  @ViewChild(StripeCardComponent) card: StripeCardComponent;
  protected customerName: string = '';
  protected totalPrice: number;
  protected basePrice: number;
  protected vouchers: any[] = [];
  protected selectedVoucher: any = 'None';

  private cartSubscription: Subscription;

  constructor(
    private stripeService: StripeService,
    private paymentService: PaymentService,
    private cartService: CartService,
    private voucherService: VoucherService,
    private toastrService: ToastrService,
    private router: Router,
  ) { }

  ngOnInit() {
    this.cartSubscription = this.cartService.getCartState().subscribe(cartState => {
      this.basePrice = cartState.totalPrice;
      this.totalPrice = this.basePrice;
    });

    this.voucherService.getMyVouchers().subscribe({
      next: (res) => {
        this.vouchers = res;
      }
    });
  }

  updateTotalPrice() {
    if (this.selectedVoucher !== 'None') {
      let voucher = this.vouchers.find(voucher => voucher.id == this.selectedVoucher);
      this.totalPrice = this.basePrice * (1 - voucher.discount / 100);
    } else {
      this.totalPrice = this.basePrice;
    }
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
        .createToken(this.card.element, {name: this.customerName})
        .subscribe(result => {
          if (result.token) {
            let voucherId = 0;
            if (this.selectedVoucher != 'None') {
              voucherId = this.selectedVoucher;
            }

            let request = {stripeToken: result.token.id, voucherId: voucherId};
            this.paymentService.createCharge(request)
              .subscribe({
                next: () => {
                  this.toastrService.success("Payment successful!");
                  this.router.navigate(['/home']).then(r => {
                  });
                },
                error: () => {
                  this.toastrService.error('Payment failed!');
                }
              });
          }
        });
    }
  }
}
