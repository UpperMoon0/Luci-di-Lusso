import {Component, OnInit} from '@angular/core';
import { DeliveryService } from "../service/delivery.service";
import {ToastrService} from "ngx-toastr";

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
    this.getDeliveries();
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
