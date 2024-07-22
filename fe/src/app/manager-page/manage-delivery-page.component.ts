import {Component, OnInit} from '@angular/core';
import {ManagerService} from "../service/manager.service";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-manage-delivery-page',
  templateUrl: './manage-delivery-page.component.html',
  styleUrls: ['./manage-delivery-page.component.css']
})
export class ManageDeliveryPageComponent implements OnInit{
  deliveries: any[] = [];
  deliverers: any[] = [];

  constructor(private managerService: ManagerService,
              private titleService: Title) {}

  ngOnInit() {
    this.getDeliverers();
    this.getDeliveries();
    this.titleService.setTitle('Delivery | Luci di Lusso');
  }

  getDeliveries(): void {
    this.managerService.getAllDeliveries().subscribe(response => {
      this.deliveries = response.unassignedDeliveries;
    });
  }

  getDeliverers(): void {
    this.managerService.getAllDeliverers().subscribe(response => {
      this.deliverers = response;
    });
  }

  assignDeliverer(deliveryId: number, delivererId: any) {
    if (delivererId == 'None') {
      return;
    }

    let request = {deliveryId: deliveryId, delivererId: delivererId};
    this.managerService.assignDeliverer(request).subscribe(response => {
      this.getDeliveries();
    });
  }
}
