import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ManagerService} from "../../service/manager.service";
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";
import {JewelryEditFormComponent} from "./jewelry-edit-form.component";
import {ConfirmDeleteComponent} from "../confirm-delete.component";
import {JewelryService} from "../../service/jewelry.service";
import {EditFormComponent} from "../edit-form.component";

interface ArrayField {
  initValue: number;
  values: {name: string; value: number}[];
}

@Component({
  selector: 'app-manager-jewelry-list',
  templateUrl: './manager-jewelry-list.component.html',
  styleUrls: ['./manager-jewelry-list.component.css'],
  encapsulation: ViewEncapsulation.ShadowDom
})
export class ManagerJewelryListComponent implements OnInit {
  protected jewelries: any[] = [];
  protected jewelryTypes: any = {};
  protected jewelrySizes: any = {};
  protected tab = 0;

  constructor(private jewelryService: JewelryService,
              private titleService: Title,
              private toastrService: ToastrService,
              public dialog: MatDialog) {}

  ngOnInit() {
    this.titleService.setTitle('Jewelries list');
    this.getJewelries();
    this.getJewelryTypes();
    this.getJewelrySizes();
  }

  setTab(tabIndex: number): void {
    this.tab = tabIndex;
  }

  getJewelries(): void {
    this.jewelryService.getAllJeweleries().subscribe(response => {
      this.jewelries = response;
    });
    this.dialog.closeAll();
  }

  getJewelryTypes(): void {
    this.jewelryService.getAllJewelryTypes().subscribe(response => {
      this.jewelryTypes = response;
    });
    this.dialog.closeAll();
  }

  getJewelrySizes(): void {
    this.jewelryService.getAllJewelrySizes().subscribe(response => {
      this.jewelrySizes = response;
    });
    this.dialog.closeAll();
  }

  openJewelryDialog(jewelry: any): void {
    this.dialog.open(JewelryEditFormComponent, {
      data: {
        jewelry: jewelry,
        refreshList: () => this.getJewelries()
      },
      width: '400px',
    });
  }

  toggleJewelryStatus(jewelryId: any) {
    this.jewelryService.toggleJewelryStatus(jewelryId).subscribe({
      next: () => {
        this.toastrService.success('Jewelry status updated successfully');
        this.getJewelries();
      },
      error: () => this.toastrService.error('Failed to update jewelry status')
    });
  }

  openJewelryTypeDialog(jewelryType: any): void {
    let fields:any[] = [
      {name: 'id', label: 'none', type: 'number', value: 0},
      {name: 'type', label: 'Type', type: 'string', value: ''},
    ];

    if (jewelryType) {
      fields = [
        {name: 'id', label: 'none', type: 'number', value: jewelryType.id},
        {name: 'type', label: 'Type', type: 'string', value: jewelryType.type},
      ];
    }

    this.dialog.open(EditFormComponent, {
      data: {
        fields: fields,
        save: (jewelryType: any) => this.saveJewelryType(jewelryType)
      },
      width: '400px',
    });
  }

  saveJewelryType(jewelryTypeId: any) {
    this.jewelryService.saveJewelryType(jewelryTypeId).subscribe({
      next: () => {
        this.toastrService.success('Jewelry type saved successfully');
        this.getJewelryTypes();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }

  toggleJewelryTypeStatus(jewelryType: any) {
    this.jewelryService.toggleJewelryTypeStatus(jewelryType).subscribe({
      next: () => {
        this.toastrService.success('Jewelry type status updated successfully');
        this.getJewelryTypes();
      },
      error: (error: { message: string; }) => {
        this.toastrService.error(error.message);
      }
    });
  }

  openJewelrySizeDialog(jewelrySize: any): void {
    let types: ArrayField = {
      initValue: this.jewelryTypes[0].id,
      values: this.jewelryTypes.map((type: any) => ({
        name: type.type,
        value: type.id
      }))
    };

    let fields:any[] = [
      {name: 'id', label: 'none', type: 'number', value: 0},
      {name: 'typeId', label: 'Jewelry type', type: 'array', value: types},
      {name: 'size', label: 'Size', type: 'number', value: 1},
      {name: 'unit', label: 'Unit', type: 'string', value: 'mm'},
      {name: 'priceMultiplier', label: 'Price multiplier', type: 'number', value: 1},
    ];

    if (jewelrySize) {
      types.initValue = jewelrySize.type.id;
      fields = [
        {name: 'id', label: 'none', type: 'number', value: jewelrySize.id},
        {name: 'typeId', label: 'Jewelry type', type: 'array', value: types},
        {name: 'size', label: 'Size', type: 'number', value: jewelrySize.size},
        {name: 'unit', label: 'Unit', type: 'string', value: jewelrySize.unit},
        {name: 'priceMultiplier', label: 'Price multiplier', type: 'number', value: jewelrySize.priceMultiplier},
      ];
    }

    this.dialog.open(EditFormComponent, {
      data: {
        fields: fields,
        save: (jewelrySize: any) => this.saveJewelrySize(jewelrySize)
      },
      width: '400px',
    });
  }

  saveJewelrySize(jewelrySize: any) {
    this.jewelryService.saveJewelrySize(jewelrySize).subscribe({
      next: () => {
        this.toastrService.success('Jewelry size saved successfully');
        this.getJewelrySizes();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }

  toggleJewelrySizeStatus(jewelrySizeId: any) {
    this.jewelryService.toggleJewelrySizeStatus(jewelrySizeId).subscribe({
      next: () => {
        this.toastrService.success('Jewelry size status updated successfully');
        this.getJewelrySizes();
      },
      error: (error: { message: string; }) => {
        this.toastrService.error(error.message);
      }
    });
  }
}
