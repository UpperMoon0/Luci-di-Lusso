import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { Title } from "@angular/platform-browser";
import { ManagerService } from "../service/manager.service";
import { MatDialog } from "@angular/material/dialog";
import { DiamondEditComponent } from "./diamond-edit.component";
import {ToastrService} from "ngx-toastr";
import {DeleteConfirmComponent} from "./delete-confirm.component";
import {ProductService} from "../service/product.service";
import {EditFormComponent} from "./edit-form.component";

@Component({
  selector: 'app-diamond-list',
  templateUrl: './manage-diamond-list.component.html',
  styleUrls: ['./manage-diamond-list.component.css'],
  encapsulation: ViewEncapsulation.ShadowDom
})
export class ManageDiamondListComponent implements OnInit {
  diamondColors: any[] = [];
  diamondClarities: any[] = [];
  diamondCuts: any[] = [];
  diamondShapes: any[] = [];
  diamonds: any[] = [];
  protected tab = 0;

  constructor(private managerService: ManagerService,
              private titleService: Title,
              private toastrService: ToastrService,
              private productService: ProductService,
              public dialog: MatDialog) {}

  ngOnInit() {
    this.titleService.setTitle('Manage diamonds');
    this.getDiamonds();
    this.getDiamondProperties();
  }

  getDiamonds(): void {
    this.managerService.getAllDiamonds().subscribe(response => {
      this.diamonds = response.map((item: { id: number; color: { color: string; }; clarity: { clarity: string; }; cut: { cut: string; }; shape: { shape: string; }; carat: number; quantity: number; }) => ({
        id: item.id,
        color: item.color.color,
        clarity: item.clarity.clarity,
        cut: item.cut.cut,
        shape: item.shape.shape,
        carat: item.carat,
        quantity: item.quantity
      }));
    });
  }

  getDiamondProperties(): void {
    this.productService.getAllDiamondProperties().subscribe(response => {
      this.diamondColors = response.colors;
      this.diamondClarities = response.clarities;
      this.diamondCuts = response.cuts;
      this.diamondShapes = response.shapes;
    });
  }

  deleteDiamond(diamondId: any) {
    this.managerService.deleteDiamond(diamondId).subscribe(response => {
      this.toastrService.success('Diamond deleted successfully');
      this.getDiamonds();
    });
  }

  openEditDiamondDialog(diamond: any): void {
    this.dialog.open(DiamondEditComponent, {
      data: {
        diamond: diamond,
        refreshList: () => this.getDiamonds()
      },
      width: '400px',
    });
  }

  openDeleteConfirmDialog(diamondId: any): void {
    this.dialog.open(DeleteConfirmComponent, {
      data: {
        entity: 'diamond',
        refreshList: () => this.getDiamonds(),
        deleteEntity: () => this.deleteDiamond(diamondId),
        closeDialog: () => this.dialog.closeAll()
      },
      width: '400px',
    });
  }

  addDiamond() {
    this.managerService.addDiamond().subscribe(
      () => {
        this.toastrService.success('Diamond added successfully');
        this.getDiamonds();
      }
    )
  }

  setTab(tabIndex: number): void {
    this.tab = tabIndex;
  }

  openDiamondColorDialog(diamondColor: any): void {
    // Set fields for the edit form
    let fields:any[] = [
      {name: 'color', type: 'string', value: diamondColor.color},
      {name: 'price', type: 'number', value: diamondColor.price}
    ];

    this.dialog.open(EditFormComponent, {
      data: {
        fields: fields,
        refreshList: () => this.getDiamondProperties()
      },
      width: '400px',
    });
  }

  openDiamondClarityDialog(diamondClarity: any): void {
    // Set fields for the edit form
    let fields:any[] = [
      {name: 'clarity', type: 'string', value: diamondClarity.clarity},
      {name: 'price', type: 'number', value: diamondClarity.price}
    ];

    this.dialog.open(EditFormComponent, {
      data: {
        fields: fields,
        refreshList: () => this.getDiamondProperties()
      },
      width: '400px',
    });
  }

  openDiamondCutDialog(diamondCut: any): void {
    // Set fields for the edit form
    let fields:any[] = [
      {name: 'cut', type: 'string', value: diamondCut.cut},
      {name: 'price', type: 'number', value: diamondCut.price}
    ];

    this.dialog.open(EditFormComponent, {
      data: {
        fields: fields,
        refreshList: () => this.getDiamondProperties()
      },
      width: '400px',
    });
  }

  openDiamondShapeDialog(diamondShape: any): void {
    // Set fields for the edit form
    let fields:any[] = [
      {name: 'shape', type: 'string', value: diamondShape.shape},
      {name: 'priceMultiplier', displayedName: 'Price multiplier', type: 'number', value: diamondShape.priceMultiplier}
    ];

    this.dialog.open(EditFormComponent, {
      data: {
        fields: fields,
        refreshList: () => this.getDiamondProperties()
      },
      width: '400px',
    });
  }
}
