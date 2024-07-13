import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Title} from "@angular/platform-browser";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ManagerService} from "../service/manager.service";
import {ProductService} from "../service/product.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-manage-jewelries-list',
  templateUrl: './manage-jewelries-list.component.html',
  styleUrls: ['./manage-jewelries-list.component.css']
})
export class ManageJewelriesListComponent {
  jewelryTypes: any[] = [];
  diamonds: any[] = [];

  jewelries: any[] = [];
  searchDiamondForm!: FormGroup;
  editableColumns = [
    { field: 'name', header: 'Name' },
    { field: 'settingPrice', header: 'Setting Price' },
    { field: 'laborCost', header: 'Labor Cost' },
    { field: 'description', header: 'Description' },
    { field: 'diamondId', header: 'Diamond ID' },
    { field: 'typeId', header: 'Type ID' },
    { field: 'imageUrl', header: 'Image Url' },
  ];
  displayedColumns = ['id', ...this.editableColumns.map(c => c.field), 'save', 'delete'];

  constructor(private managerService: ManagerService,
              private productService: ProductService,
              private toastrService: ToastrService,
              private titleService: Title,
              private fb: FormBuilder,
              private snackBar: MatSnackBar) {}

  ngOnInit() {
    this.getJewelries();
    this.getDiamonds();
    this.getAllJewelryTypes();
    this.titleService.setTitle('Diamond List | Luci di Lusso');
    this.searchDiamondForm = this.fb.group({
      title: [null, [Validators.minLength(1)]]
    });
  }

  getAllJewelryTypes(): void {
    this.productService.getAllTypes().subscribe(response => {
      this.jewelryTypes = response.types;
    });
  }

  getDiamonds(): void {
    this.diamonds = [];
    this.managerService.getAllDiamonds().subscribe(response => {
      this.diamonds = response.diamonds;
    });
  }

  getJewelries(): void {
    this.jewelries = [];
    this.managerService.getAllJeweleries().subscribe(response => {
      this.jewelries = response.jewelries.map(d => ({ ...d, isEditMode: false }));
    });
  }

  deleteJewelry(jewelryId: number) {
    this.managerService.deleteJewelry(jewelryId).subscribe(response => {
      if (response == null) {
        if (typeof window !== 'undefined') {
          localStorage.setItem('showSnackbar', 'true');
        }
        window.location.reload();
      } else {
        this.snackBar.open(response.message, 'Close', {
          duration: 5000,
          panelClass: 'error-snackbar'
        });
      }
    });
  }

  editJewelry(jewelry: any) {
    this.jewelries = this.jewelries.map(d => {
      if (d.id === jewelry.id) {
        return { ...d, isEditMode: true };
      }
      return d;
    });
  }

  saveJewelry(editedJewelry: any) {
    const { isEditMode, ...jewelryToSave } = editedJewelry;
    this.managerService.updateJewelry(jewelryToSave).subscribe({
      next: (response) => {
        this.snackBar.open('Jewelry updated successfully', 'Close', {
          duration: 5000,
          panelClass: 'success-snackbar'
        });
        this.getJewelries(); // Refresh the list
      },
      error: () => {
        this.snackBar.open('Error updating jewelry', 'Close', {
          duration: 5000,
          panelClass: 'error-snackbar'
        });
      }
    });
  }
}
