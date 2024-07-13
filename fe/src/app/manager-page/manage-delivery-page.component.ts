import {Component, OnInit} from '@angular/core';
import {ManagerService} from "../service/manager.service";
import {Title} from "@angular/platform-browser";
import {MatSnackBar} from "@angular/material/snack-bar";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-manage-delivery-page',
  templateUrl: './manage-delivery-page.component.html',
  styleUrls: ['./manage-delivery-page.component.css']
})
export class ManageDeliveryPageComponent implements OnInit{
  deliveries: any[] = [];
  deliverers: any[] = [];

  delivererID: number;
  deliveryID: number;
  form = this.formBuilder.group({
    delivererID: new FormControl(null, [Validators.required]),
  });

  constructor(private managerService: ManagerService,
              private formBuilder: FormBuilder,
              private titleService: Title,
              private snackBar: MatSnackBar) {}

  ngOnInit() {
    this.getDeliverers();
    this.getDeliveries();
    this.titleService.setTitle('Delivery | Luci di Lusso');
  }

  setDeliveryID(deliveryID: number) {
    this.deliveryID = deliveryID;
  }

  submitForm() {
    this.delivererID = this.form.value.delivererID;
    this.saveDelivery();
    //reset delivererID in form
    this.form.value.delivererID = null;
  }

  getDeliveries(): void {
    this.deliveries = [];
    this.managerService.getAllDeliveries().subscribe(response => {
      this.deliveries = response.deliveries;
    });
  }

  getDeliverers(): void {
    this.deliverers = [];
    this.managerService.getAllDeliverers().subscribe(response => {
      this.deliverers = response.deliverers;
    });
  }

  saveDelivery() {
    const request = {
      deliveryID: this.deliveryID,
      delivererID: this.delivererID
    }
    this.managerService.assignDelivery(request).subscribe({
      next: () => {
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
