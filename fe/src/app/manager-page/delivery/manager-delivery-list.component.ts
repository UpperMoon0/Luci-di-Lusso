import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ToastrService} from "ngx-toastr";
import {DeliveryService} from "../../service/delivery.service";
import {DeliveryDetailsComponent} from "./delivery-details.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-manager-delivery-list',
  templateUrl: './manager-delivery-list.component.html',
  styleUrls: ['./manager-delivery-list.component.css'],
  encapsulation: ViewEncapsulation.ShadowDom
})
export class ManagerDeliveryListComponent implements OnInit{
  protected deliveries: any[] = [];
  protected deliverers: any[] = [];

  constructor(private deliveryService: DeliveryService,
              private titleService: Title,
              private toastrService: ToastrService,
              private dialog: MatDialog) {}

  ngOnInit() {
    this.getDeliverers();
    this.getDeliveries();
    this.titleService.setTitle('Order list');
  }

  getDeliveries(): void {
    this.deliveryService.getAllDeliveries().subscribe(response => {
      this.deliveries = response.deliveries.map(delivery => {
        if (!delivery.delivery.deliverer) {
          delivery.delivery.deliverer = { id: 'None' };
        }
        return delivery;
      });
    });
  }

  getDeliverers(): void {
    this.deliveryService.getAllDeliverers().subscribe(response => {
      this.deliverers = response;
    });
  }

  assignDeliverer(deliveryId: number, delivererId: any) {
    if (delivererId == 'None') {
      return;
    }

    let request = {deliveryId: deliveryId, delivererId: delivererId};
    this.deliveryService.assignDeliverer(request).subscribe(response => {
      this.getDeliveries();
      this.toastrService.success('Delivery staff assigned successfully');
    });
  }

  unassignDeliverer(deliveryId: number) {
    this.deliveryService.unassignDeliverer(deliveryId).subscribe(response => {
      this.getDeliveries();
      this.toastrService.success('Delivery staff unassigned successfully');
    });
  }

  openDeliveryDetailsDialog(delivery: any): void {
    this.dialog.open(DeliveryDetailsComponent, {
      data: {
        delivery: delivery
      },
      width: '800px',
    });
  }
}
