import {Component, OnInit} from '@angular/core';
import {ManagerService} from "../service/manager.service";
import {Title} from "@angular/platform-browser";
import {FormBuilder, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-manage-delivery-page',
  templateUrl: './manage-delivery-page.component.html',
  styleUrls: ['./manage-delivery-page.component.css']
})
export class ManageDeliveryPageComponent implements OnInit{
  deliveries: any[] = [];
  deliverers: any[] = [];

  deliverer: number;

  constructor(private managerService: ManagerService,
              private titleService: Title,
              private fb: FormBuilder,
              private snackBar: MatSnackBar) {}

  ngOnInit() {
    this.getDeliverers();
    this.getDeliveries();
    this.titleService.setTitle('Delivery | Luci di Lusso');
  }

  getDeliveries(): void {
    this.deliveries = [];
    this.managerService.getAllDeliveries().subscribe(response => {
      this.deliveries = response;
    });
  }

  getDeliverers(): void {
    this.deliverers = [];
    this.managerService.getAllDeliverers().subscribe(response => {
      this.deliverers = response;
    });
  }

  saveDelivery(deliveryID: number) {
    const request = {
      deliveryID: deliveryID,
      delivererID: this.deliverer
    }
    this.managerService.assignDelivery(request).subscribe({
      next: (response) => {
        this.snackBar.open('Delivery updated successfully', 'Close', {
          duration: 5000,
          panelClass: 'success-snackbar'
        });
        this.getDeliveries(); // Refresh the list
      },
      error: () => {
        this.snackBar.open('Error updating delivery', 'Close', {
          duration: 5000,
          panelClass: 'error-snackbar'
        });
      }
    });
  }

  protected readonly document = document;
  protected readonly Number = Number;
}
