import {Component, OnInit} from '@angular/core';
import { DeliveryService } from "../service/delivery.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-delivery-dashboard',
  templateUrl: './delivery-dashboard.component.html',
  styleUrls: ['./delivery-dashboard.component.css']
})
export class DeliveryDashboardComponent implements OnInit {


  deliveries : any[] = [];

  constructor(private deliveryService: DeliveryService,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.getDeliveries();
  }

  private getDeliveries() {
    this.deliveryService.getDeliveries().subscribe({
      next: (deliveries) => this.deliveries = deliveries,
      error: () => this.toastrService.error("Error in getting products list")
    });
  }

  public checkDeliveryStatus(id: number) {
    let msg: string;
    this.deliveryService.checkDelivery(id).subscribe(res => {
      msg = res;
    })
    this.toastrService.success(msg);
  }

}
