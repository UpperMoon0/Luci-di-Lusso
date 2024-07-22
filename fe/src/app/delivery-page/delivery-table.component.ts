import {Component, OnInit} from '@angular/core';
import { DeliveryService } from "../service/delivery.service";
import {ToastrService} from "ngx-toastr";
import { DeliveryPageComponent } from "./delivery-page.component";

@Component({
  selector: 'app-delivery-table',
  templateUrl: './delivery-table.component.html',
  styleUrls: ['./delivery-table.component.css']
})
export class DeliveryTableComponent implements OnInit {
  deliveries : any[] = [];

  constructor(private deliveryService: DeliveryService,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.getMyDeliveries();
  }
  private getMyDeliveries() {
    this.deliveryService.getMyDeliveries().subscribe({
      next: (res) => this.deliveries = res.deliveries,
      error: () => this.toastrService.error()
    });
  }

  public completeDelivery(id: number) {
    this.deliveryService.completeDelivery(id).subscribe({
      next: () => {
        this.toastrService.success("Delivery completed successfully");
        this.getMyDeliveries();
      },
      error: () => {
        this.toastrService.error();
      }
    });
  }
}
