import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-purchase-details',
  templateUrl: './purchase-details.component.html',
})
export class PurchaseDetailsComponent {
  @Input() order: any = null;

  constructor() {
  }

  getTotalPrice(order: any): number {
    let totalPrice: number = 0;
    for (let orderItem of order.orderItems) {
      totalPrice += orderItem.price * orderItem.quantity;
    }
    return totalPrice;
  }

  getItemCount(order: any): number {
    let itemCount: number = 0;
    for (let orderItem of order.orderItems) {
      itemCount += orderItem.quantity;
    }
    return itemCount;
  }
}
