import {Component, OnInit} from '@angular/core';
import {OrderService} from "../service/order.service";
import {DeliveryPageComponent} from "./delivery-page.component";

@Component({
  selector: 'app-list-of-orders',
  templateUrl: './list-of-orders.component.html',
  styleUrls: ['./list-of-orders.component.css']
})
export class ListOfOrdersComponent implements OnInit {

  deliveryID : number;
  listOfOrder : any[] = [];

  constructor(private deliveryPageComponent: DeliveryPageComponent,
              private orderService : OrderService) {
  }

  public ngOnInit() {
    this.deliveryID = this.deliveryPageComponent.getChoseDeliveryID();
    this.getOrderDetail(this.deliveryID);
    this.getOrderDetail(this.deliveryID);
  }

  getOrderDetail(id: number) {
    this.orderService.getOrderDetails(id).subscribe( response => {
      this.listOfOrder = response.productList;
    });
  }

}
