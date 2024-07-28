import {Component, Inject, OnInit} from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {ProductService} from "../../service/product.service";
import {ToastrService} from "ngx-toastr";
import {DiamondService} from "../../service/diamond.service";
import {ManagerService} from "../../service/manager.service";

@Component({
  selector: 'app-jewelry-edit-form',
  templateUrl: './jewelry-edit-form.component.html',
  styleUrls: ['./jewelry-edit-form.component.css']
})
export class JewelryEditFormComponent implements OnInit {
  protected jewelryTypes: any = {};
  protected diamonds: any = {};
  protected initialType: any = {};
  protected initialDiamond: any = {};

  jewelryEditForm = new FormGroup({
    name: new FormControl(this.data.jewelry.name),
    description: new FormControl(this.data.jewelry.description),
    diamond: new FormControl(this.initialDiamond),
    type: new FormControl(this.initialType),
    imageUrl: new FormControl(this.data.jewelry.imageUrl),
    settingPrice: new FormControl(this.data.jewelry.settingPrice),
    laborCost: new FormControl(this.data.jewelry.laborCost),
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private productService: ProductService,
    private diamondService: DiamondService,
    private managerService: ManagerService,
    private toastrService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.diamondService.getAllDiamonds().subscribe(response => {
      this.diamonds = response;

      // Set selected values using find
      this.initialDiamond = this.diamonds.find((diamond: any) => diamond.id === this.data.jewelry.diamond.id);

      // Update form controls with the found values
      this.jewelryEditForm.patchValue({
        diamond: this.initialDiamond,
      });
    });

    this.productService.getAllTypes().subscribe(response => {
      this.jewelryTypes = response.types;

      // Set selected values using find
      this.initialType = this.jewelryTypes.find((type: any) => type.type === this.data.jewelry.type.type);

      // Update form controls with the found values
      this.jewelryEditForm.patchValue({
        type: this.initialType,
      });
    });

    this.jewelryEditForm.patchValue({
      name: this.data.jewelry.name,
      description: this.data.jewelry.description,
      imageUrl: this.data.jewelry.imageUrl,
      settingPrice: this.data.jewelry.settingPrice,
      laborCost: this.data.jewelry.laborCost,
    });
  }

  onSubmit(): void {
    let jewelry = {
      id: this.data.jewelry.id,
      name: this.jewelryEditForm.value.name,
      description: this.jewelryEditForm.value.description,
      diamondId: this.jewelryEditForm.value.diamond.id,
      typeId: this.jewelryEditForm.value.type.id,
      imageUrl: this.jewelryEditForm.value.imageUrl,
      settingPrice: this.jewelryEditForm.value.settingPrice,
      laborCost: this.jewelryEditForm.value.laborCost,
    };

    this.managerService.updateJewelry(jewelry).subscribe({
        next: () => {
          this.toastrService.success('Jewelry updated successfully');
          this.data.refreshList();
        },
        error: () => {
          this.toastrService.error('Failed to update jewelry');
        }
    });
  }
}
