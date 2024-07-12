import {Component, OnInit} from '@angular/core';
import { DeliveryTableComponent } from '../delivery-table.component';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css']
})
export class OrdersListComponent implements OnInit{

  deliveryID : number;

  constructor(private deliveryTableComponent: DeliveryTableComponent) {
  }

  public ngOnInit() {
    this.deliveryID = this.deliveryTableComponent.deliveryID;
  }

}
