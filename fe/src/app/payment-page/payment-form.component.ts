import {Component, ViewChild, OnInit, OnDestroy, inject} from '@angular/core';
import { StripeService, StripeCardComponent } from 'ngx-stripe';
import { PaymentService } from "../service/payment.service";
import { CartService } from "../service/cart.service";
import { Subscription } from 'rxjs';
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {MatButtonModule} from '@angular/material/button';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {FormsModule} from "@angular/forms";
import {VoucherWindowComponent} from "./voucher-window/voucher-window.component";
import {VouchersService} from "../service/vouchers.service";

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html',
  styleUrls: ['./payment-form.component.css']
})
export class PaymentFormComponent implements OnInit, OnDestroy {
  @ViewChild(StripeCardComponent) card: StripeCardComponent;
  customerName: string = '';
  totalPrice: number;
  private cartSubscription: Subscription;

  vouchersList: any[] = []
  chosenVoucher: any;

  constructor(
    private stripeService: StripeService,
    private paymentService: PaymentService,
    private cartService: CartService,
    private toastrService: ToastrService,
    private router: Router,
    public dialog: MatDialog,
    public vouchersService: VouchersService,
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
            this.paymentService.createCharge(result.token.id, this.chosenVoucher?.code)
              .subscribe({
                next: () => {
                  this.toastrService.success("Payment successful!");
                  this.router.navigate(['/home']).then(r => {});
                },
                error: () => {
                  this.toastrService.error('Payment failed!');
                }
              });
          }
        });
    }
  }

  getAllCustomerVouchers() {
    this.vouchersService.getAllCustomerVouchers().subscribe({
      next: (res) => { this.vouchersList = res.vouchers; }
    })
  }

  openDialog(): void {
    if (this.vouchersList.length > 0) {
      const dialogRef = this.dialog.open(VoucherWindowComponent, {
        width: '600px',
        data: { vouchersList: this.vouchersList }
      });
      dialogRef.afterClosed().subscribe(result => {
        if (result !== undefined) {
          this.chosenVoucher = result;
        }
      });
    } else {
      this.toastrService.error('No voucher found!');
    }
  }
}
