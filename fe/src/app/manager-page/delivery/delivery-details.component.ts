import {Component, Inject, OnInit, ViewEncapsulation} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-delivery-details',
  templateUrl: './delivery-details.component.html',
  styleUrls: ['./delivery-details.component.css'],
  encapsulation: ViewEncapsulation.ShadowDom
})
export class DeliveryDetailsComponent implements OnInit {
  protected delivery: any;
  protected totalItems: number = 0;
  protected totalPrice: number = 0;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit(): void {
    this.delivery = this.data.delivery;
    this.totalItems = this.delivery.orderItems.reduce((sum: number, item: { quantity: number; }) => sum + item.quantity, 0);
    this.totalPrice = this.delivery.orderItems.reduce((sum: number, item: { price: number; quantity: number; }) => sum + (item.price * item.quantity), 0);
  }
}
