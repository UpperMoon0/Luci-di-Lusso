import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-purchase-details',
  templateUrl: './purchase-details.component.html',
})
export class PurchaseDetailsComponent {
  @Input() orderDetails: { customerName: string; totalPrice: number; createAt: string };

  constructor() {
  }
}
