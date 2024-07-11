import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-purchase-details',
  templateUrl: './purchase-details.component.html',
})
export class PurchaseDetailsComponent {
  @Input() orderDetails: { customerName: string; totalPrice: number; createAt: string };
  @Input() productList: { name: string; size: string; price: number; quantity: number; type: string; imageUrl: string }[];


  constructor() {
  }
}
