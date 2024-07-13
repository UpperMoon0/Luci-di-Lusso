import {Component, OnInit} from '@angular/core';
import { DeliveryTableComponent } from './delivery-table.component';
import {OrderService} from "../service/order.service";

@Component({
  selector: 'app-list-of-orders',
  templateUrl: './list-of-orders.component.html',
  styleUrls: ['./list-of-orders.component.css']
})
export class ListOfOrdersComponent implements OnInit {

  deliveryID : number;
  listOfOrder : any[] = [];

  constructor(private deliveryTableComponent: DeliveryTableComponent,
              private orderService : OrderService) {
  }

  public ngOnInit() {
    this.deliveryID = this.deliveryTableComponent.getDeliveryID();
  }

  getOrderDetail(id: number) {
    this.orderService.getOrderDetails(id).subscribe( response => {
      this.listOfOrder = response.productList;
    });
  }

}
