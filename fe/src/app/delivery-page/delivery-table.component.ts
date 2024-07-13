import {Component, OnInit} from '@angular/core';
import { DeliveryService } from "../service/delivery.service";
import {ToastrService} from "ngx-toastr";
import { DeliveryPageComponent } from "./delivery-page.component";
import {ListOfOrdersComponent} from "./list-of-orders.component";

@Component({
  selector: 'app-delivery-table',
  templateUrl: './delivery-table.component.html',
  styleUrls: ['./delivery-table.component.css']
})
export class DeliveryTableComponent implements OnInit {
  deliveries : any[] = [];

  constructor(private deliveryService: DeliveryService,
              private toastrService: ToastrService,
              private deliveryPageComponent: DeliveryPageComponent) {
  }

  ngOnInit(): void {
    this.getDeliveries();
  }

  public setDeliveryID(id : number) {
    if (this.deliveryPageComponent.getChoseDeliveryID() === id) {
      this.deliveryPageComponent.setChoseDeliveryID(0);
      this.deliveryPageComponent.modifyOpen(0);
    } else {
      this.deliveryPageComponent.setChoseDeliveryID(id);
      this.deliveryPageComponent.modifyOpen(1);
    }
  }

  private getDeliveries() {
    this.deliveryService.getDeliveries().subscribe({
      next: (res) => this.deliveries = res.deliveries,
      error: () => this.toastrService.error()
    });
  }

  public completeDelivery(id: number) {
    this.deliveryService.completeDelivery(id).subscribe({
      next: (res) => {
        this.toastrService.success(res.message);
        this.getDeliveries();
      },
      error: () => {
        this.toastrService.error();
      }
    });
  }
}
