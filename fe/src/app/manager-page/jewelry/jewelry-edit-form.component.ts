import {Component, Inject, OnInit} from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {JewelryService} from "../../service/jewelry.service";
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
  private jewelryId: number = 0;

  jewelryEditForm = new FormGroup({
    name: new FormControl(),
    description: new FormControl(),
    diamond: new FormControl(),
    type: new FormControl(),
    imageUrl: new FormControl,
    settingPrice: new FormControl(),
    laborCost: new FormControl(),
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private jewelryService: JewelryService,
    private diamondService: DiamondService,
    private toastrService: ToastrService,
  ) { }

  ngOnInit(): void {
    // Set initial values
    let initialName: any = '';
    let initialDescription: any = '';
    let initialImageUrl: any = '';
    let initialSettingPrice: any = '';
    let initialLaborCost: any = '';

    // Set initial values if data is present
    if (this.data.jewelry) {
      this.jewelryId = this.data.jewelry.id;
      initialName = this.data.jewelry.name;
      initialDescription = this.data.jewelry.description;
      initialImageUrl = this.data.jewelry.imageUrl;
      initialSettingPrice = this.data.jewelry.settingPrice;
      initialLaborCost = this.data.jewelry.laborCost;
    }

    // Update form controls with the found values
    this.jewelryEditForm.patchValue({
      name: initialName,
      description: initialDescription,
      imageUrl: initialImageUrl,
      settingPrice: initialSettingPrice,
      laborCost: initialLaborCost,
    });

    this.diamondService.getAllDiamonds().subscribe(response => {
      this.diamonds = response;

      let initialDiamond = this.diamonds[0];

      if (this.data.jewelry) {
        initialDiamond = this.diamonds.find((diamond: any) => diamond.id === this.data.jewelry.diamond.id);
      }

      this.jewelryEditForm.patchValue({
        diamond: initialDiamond,
      });
    });

    this.jewelryService.getAllTypes().subscribe(response => {
      this.jewelryTypes = response.types;

      let initialType = this.jewelryTypes[0];

      if (this.data.jewelry) {
        initialType = this.jewelryTypes.find((type: any) => type.type === this.data.jewelry.type.type);
      }

      this.jewelryEditForm.patchValue({
        type: initialType,
      });
    });
  }

  save(): void {
    let jewelry = {
      id: this.jewelryId,
      name: this.jewelryEditForm.value.name,
      description: this.jewelryEditForm.value.description,
      diamondId: this.jewelryEditForm.value.diamond.id,
      typeId: this.jewelryEditForm.value.type.id,
      imageUrl: this.jewelryEditForm.value.imageUrl,
      settingPrice: this.jewelryEditForm.value.settingPrice,
      laborCost: this.jewelryEditForm.value.laborCost,
    };

    this.jewelryService.saveJewelry(jewelry).subscribe({
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
