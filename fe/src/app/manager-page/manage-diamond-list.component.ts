import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { Title } from "@angular/platform-browser";
import { ManagerService } from "../service/manager.service";
import { MatDialog } from "@angular/material/dialog";
import { DiamondEditComponent } from "./diamond-edit.component";
import {ToastrService} from "ngx-toastr";
import {ConfirmDeleteComponent} from "./confirm-delete.component";
import {ProductService} from "../service/product.service";
import {EditFormComponent} from "./edit-form.component";
import {DiamondService} from "../service/diamond.service";

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
              private diamondService: DiamondService,
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

  openDiamondConfirmDeleteDialog(diamondId: any): void {
    this.dialog.open(ConfirmDeleteComponent, {
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
      {name: 'id', label: 'none', type: 'number', value: diamondColor.id},
      {name: 'color', label: 'Color', type: 'string', value: diamondColor.color},
      {name: 'price', label: 'Price', type: 'number', value: diamondColor.price}
    ];

    this.dialog.open(EditFormComponent, {
      data: {
        fields: fields,
        save: (diamondColor: any) => this.saveDiamondColor(diamondColor)
      },
      width: '400px',
    });
  }

  openDiamondClarityDialog(diamondClarity: any): void {
    // Set fields for the edit form
    let fields:any[] = [
      {name: 'id', label: 'none', type: 'number', value: diamondClarity.id},
      {name: 'clarity', label: 'Clarity', type: 'string', value: diamondClarity.clarity},
      {name: 'price', label: 'Price', type: 'number', value: diamondClarity.price}
    ];

    this.dialog.open(EditFormComponent, {
      data: {
        fields: fields,
        save: (diamondClarity: any) => this.saveDiamondClarity(diamondClarity)
      },
      width: '400px',
    });
  }

  openDiamondCutDialog(diamondCut: any): void {
    // Set fields for the edit form
    let fields:any[] = [
      {name: 'id', label: 'none', type: 'number', value: diamondCut.id},
      {name: 'cut', label: 'Cut', type: 'string', value: diamondCut.cut},
      {name: 'price', label: 'Price', type: 'number', value: diamondCut.price}
    ];

    this.dialog.open(EditFormComponent, {
      data: {
        fields: fields,
        save: (diamondCut: any) => this.saveDiamondCut(diamondCut)
      },
      width: '400px',
    });
  }

  openDiamondShapeDialog(diamondShape: any): void {
    // Set fields for the edit form
    let fields:any[] = [
      {name: 'id', label: 'none', type: 'number', value: diamondShape.id},
      {name: 'shape', label: 'Shape', type: 'string', value: diamondShape.shape},
      {name: 'priceMultiplier', label: 'Price multiplier', type: 'number', value: diamondShape.priceMultiplier}
    ];

    this.dialog.open(EditFormComponent, {
      data: {
        fields: fields,
        save: (diamondShape: any) => this.saveDiamondShape(diamondShape)
      },
      width: '400px',
    });
  }

  saveDiamondColor(diamondColor: any) {
    this.diamondService.saveDiamondColor(diamondColor).subscribe({
      next: () => {
        this.toastrService.success('Diamond color saved successfully');
        this.dialog.closeAll();
        this.getDiamondProperties();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }

  saveDiamondClarity(diamondClarity: any) {
    this.diamondService.saveDiamondClarity(diamondClarity).subscribe({
      next: () => {
        this.toastrService.success('Diamond clarity saved successfully');
        this.dialog.closeAll();
        this.getDiamondProperties();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }

  saveDiamondCut(diamondCut: any) {
    this.diamondService.saveDiamondCut(diamondCut).subscribe({
      next: () => {
        this.toastrService.success('Diamond cut saved successfully');
        this.dialog.closeAll();
        this.getDiamondProperties();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }

  saveDiamondShape(diamondShape: any) {
    this.diamondService.saveDiamondShape(diamondShape).subscribe({
      next: () => {
        this.toastrService.success('Diamond shape saved successfully');
        this.dialog.closeAll();
        this.getDiamondProperties();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }

  openDiamondColorConfirmDeleteDialog(diamondColorId: any): void {
    this.dialog.open(ConfirmDeleteComponent, {
      data: {
        entity: 'diamond color',
        deleteEntity: () => this.deleteDiamondColor(diamondColorId),
        closeDialog: () => this.dialog.closeAll()
      },
      width: '400px',
    });
  }

  openDiamondClarityConfirmDeleteDialog(diamondClarityId: any): void {
    this.dialog.open(ConfirmDeleteComponent, {
      data: {
        entity: 'diamond clarity',
        deleteEntity: () => this.deleteDiamondClarity(diamondClarityId),
        closeDialog: () => this.dialog.closeAll()
      },
      width: '400px',
    });
  }

  openDiamondCutConfirmDeleteDialog(diamondCutId: any): void {
    this.dialog.open(ConfirmDeleteComponent, {
      data: {
        entity: 'diamond cut',
        deleteEntity: () => this.deleteDiamondCut(diamondCutId),
        closeDialog: () => this.dialog.closeAll()
      },
      width: '400px',
    });
  }

  openDiamondShapeConfirmDeleteDialog(diamondShapeId: any): void {
    this.dialog.open(ConfirmDeleteComponent, {
      data: {
        entity: 'diamond shape',
        deleteEntity: () => this.deleteDiamondShape(diamondShapeId),
        closeDialog: () => this.dialog.closeAll()
      },
      width: '400px',
    });
  }

  deleteDiamondColor(diamondColorId: any) {
    this.diamondService.deleteDiamondColor(diamondColorId).subscribe({
      next: () => {
        this.toastrService.success('Diamond color deleted successfully');
        this.dialog.closeAll();
        this.getDiamondProperties();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }

  deleteDiamondClarity(diamondClarityId: any) {
    this.diamondService.deleteDiamondClarity(diamondClarityId).subscribe({
      next: () => {
        this.toastrService.success('Diamond clarity deleted successfully');
        this.dialog.closeAll();
        this.getDiamondProperties();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }

  deleteDiamondCut(diamondCutId: any) {
    this.diamondService.deleteDiamondCut(diamondCutId).subscribe({
      next: () => {
        this.toastrService.success('Diamond cut deleted successfully');
        this.dialog.closeAll();
        this.getDiamondProperties();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }

  deleteDiamondShape(diamondShapeId: any) {
    this.diamondService.deleteDiamondShape(diamondShapeId).subscribe({
      next: () => {
        this.toastrService.success('Diamond shape deleted successfully');
        this.dialog.closeAll();
        this.getDiamondProperties();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }
}
