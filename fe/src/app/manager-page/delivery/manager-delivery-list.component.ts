import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ManagerService} from "../../service/manager.service";
import {Title} from "@angular/platform-browser";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-manager-delivery-list',
  templateUrl: './manager-delivery-list.component.html',
  styleUrls: ['./manager-delivery-list.component.css'],
  encapsulation: ViewEncapsulation.ShadowDom
})
export class ManagerDeliveryListComponent implements OnInit{
  deliveries: any[] = [];
  deliverers: any[] = [];

  constructor(private managerService: ManagerService,
              private titleService: Title,
              private toastrService: ToastrService) {}

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
      this.toastrService.success('Deliverer assigned successfully');
    });
  }
}
