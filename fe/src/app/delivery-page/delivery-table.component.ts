import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { DeliveryService } from "../service/delivery.service";
import {ToastrService} from "ngx-toastr";
import { DeliveryPageComponent } from "./delivery-page.component";
import {DeliveryDetailsComponent} from "../manager-page/delivery/delivery-details.component";
import {MatDialog} from "@angular/material/dialog";
import {CompleteDeliveryConfirmComponent} from "./complete-delivery-confirm.component";

@Component({
  selector: 'app-delivery-table',
  templateUrl: './delivery-table.component.html',
  styleUrls: ['./delivery-table.component.css'],
  encapsulation: ViewEncapsulation.ShadowDom
})
export class DeliveryTableComponent implements OnInit {
  protected deliveries : any[] = [];

  constructor(private deliveryService: DeliveryService,
              private toastrService: ToastrService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.getMyDeliveries();
  }

  private getMyDeliveries() {
    this.deliveryService.getMyDeliveries().subscribe({
      next: (res) => this.deliveries = res.deliveries,
      error: () => this.toastrService.error("Failed to load deliveries")
    });
    this.dialog.closeAll();
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

  openDeliveryDetailsDialog(delivery: any): void {
    this.dialog.open(DeliveryDetailsComponent, {
      data: {
        delivery: delivery
      },
      width: '800px',
    });
  }

  openCompleteDeliveryConfirmDialog(id: number): void {
    this.dialog.open(CompleteDeliveryConfirmComponent, {
      data: {
        completeDelivery: () => this.completeDelivery(id),
        dialogRef: this.dialog,
      },
      width: '600px',
    });
  }
}
