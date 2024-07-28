import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { Title } from "@angular/platform-browser";
import { MatDialog } from "@angular/material/dialog";
import { DiamondEditFormComponent } from "./diamond-edit-form.component";
import {ToastrService} from "ngx-toastr";
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

  constructor(private titleService: Title,
              private toastrService: ToastrService,
              private diamondService: DiamondService,
              public dialog: MatDialog) {}

  ngOnInit() {
    this.titleService.setTitle('Manage diamonds');
    this.getDiamonds();
    this.getDiamondProperties();
  }

  setTab(tabIndex: number): void {
    this.tab = tabIndex;
  }

  getDiamonds(): void {
    this.diamondService.getAllDiamonds().subscribe(response => {
      this.diamonds = response.map((item: { id: number;
                                            color: { color: string; };
                                            clarity: { clarity: string; };
                                            cut: { cut: string; };
                                            shape: { shape: string; };
                                            carat: number;
                                            quantity: number;
                                            createAt: string;
                                            status: string;}) => ({
        id: item.id,
        color: item.color.color,
        clarity: item.clarity.clarity,
        cut: item.cut.cut,
        shape: item.shape.shape,
        carat: item.carat,
        quantity: item.quantity,
        createAt: item.createAt,
        status: item.status
      }));
    });
    this.dialog.closeAll();
  }

  getDiamondProperties(): void {
    this.diamondService.getAllDiamondProperties().subscribe(response => {
      this.diamondColors = response.colors;
      this.diamondClarities = response.clarities;
      this.diamondCuts = response.cuts;
      this.diamondShapes = response.shapes;
    });
    this.dialog.closeAll();
  }

  openDiamondDialog(diamond: any): void {
    this.dialog.open(DiamondEditFormComponent, {
      data: {
        diamond: diamond,
        refreshList: () => this.getDiamonds()
      },
      width: '400px',
    });
  }

  openDiamondColorDialog(diamondColor: any): void {
    let fields:any[] = [
      {name: 'id', label: 'none', type: 'number', value: 0},
      {name: 'color', label: 'Color', type: 'string', value: ''},
      {name: 'price', label: 'Price', type: 'number', value: 0}
    ];

    if (diamondColor) {
      fields = [
        {name: 'id', label: 'none', type: 'number', value: diamondColor.id},
        {name: 'color', label: 'Color', type: 'string', value: diamondColor.color},
        {name: 'price', label: 'Price', type: 'number', value: diamondColor.price}
      ];
    }

    this.dialog.open(EditFormComponent, {
      data: {
        fields: fields,
        save: (diamondColor: any) => this.saveDiamondColor(diamondColor)
      },
      width: '400px',
    });
  }

  openDiamondClarityDialog(diamondClarity: any): void {
    let fields:any[] = [
      {name: 'id', label: 'none', type: 'number', value: 0},
      {name: 'clarity', label: 'Clarity', type: 'string', value: ''},
      {name: 'price', label: 'Price', type: 'number', value: 0}
    ];

    if (diamondClarity) {
      fields = [
        {name: 'id', label: 'none', type: 'number', value: diamondClarity.id},
        {name: 'clarity', label: 'Clarity', type: 'string', value: diamondClarity.clarity},
        {name: 'price', label: 'Price', type: 'number', value: diamondClarity.price}
      ];
    }

    this.dialog.open(EditFormComponent, {
      data: {
        fields: fields,
        save: (diamondClarity: any) => this.saveDiamondClarity(diamondClarity)
      },
      width: '400px',
    });
  }

  openDiamondCutDialog(diamondCut: any): void {
    let fields:any[] = [
      {name: 'id', label: 'none', type: 'number', value: 0},
      {name: 'cut', label: 'Cut', type: 'string', value: ''},
      {name: 'price', label: 'Price', type: 'number', value: 0}
    ];

    if (diamondCut) {
      fields = [
        {name: 'id', label: 'none', type: 'number', value: diamondCut.id},
        {name: 'cut', label: 'Cut', type: 'string', value: diamondCut.cut},
        {name: 'price', label: 'Price', type: 'number', value: diamondCut.price}
      ];
    }

    this.dialog.open(EditFormComponent, {
      data: {
        fields: fields,
        save: (diamondCut: any) => this.saveDiamondCut(diamondCut)
      },
      width: '400px',
    });
  }

  openDiamondShapeDialog(diamondShape: any): void {
    let fields:any[] = [
      {name: 'id', label: 'none', type: 'number', value: 0},
      {name: 'shape', label: 'Shape', type: 'string', value: ''},
      {name: 'priceMultiplier', label: 'Price multiplier', type: 'number', value: 0}
    ];

    if (diamondShape) {
      fields = [
        {name: 'id', label: 'none', type: 'number', value: diamondShape.id},
        {name: 'shape', label: 'Shape', type: 'string', value: diamondShape.shape},
        {name: 'priceMultiplier', label: 'Price multiplier', type: 'number', value: diamondShape.priceMultiplier}
      ];
    }

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
        this.getDiamondProperties();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }

  toggleDiamondStatus(diamondId: any) {
    this.diamondService.toggleDiamondStatus(diamondId).subscribe({
      next: () => {
        this.toastrService.success('Diamond deleted successfully');
        this.getDiamonds();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }

  toggleDiamondColorStatus(diamondColorId: any) {
    this.diamondService.toggleDiamondColorStatus(diamondColorId).subscribe({
      next: () => {
        this.toastrService.success('Diamond color deleted successfully');
        this.getDiamondProperties();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }

  toggleDiamondClarityStatus(diamondClarityId: any) {
    this.diamondService.toggleDiamondClarityStatus(diamondClarityId).subscribe({
      next: () => {
        this.toastrService.success('Diamond clarity deleted successfully');
        this.getDiamondProperties();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }

  toggleDiamondCutStatus(diamondCutId: any) {
    this.diamondService.toggleDiamondCutStatus(diamondCutId).subscribe({
      next: () => {
        this.toastrService.success('Diamond cut deleted successfully');
        this.getDiamondProperties();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }

  toggleDiamondShapeStatus(diamondShapeId: any) {
    this.diamondService.toggleDiamondShapeStatus(diamondShapeId).subscribe({
      next: () => {
        this.toastrService.success('Diamond shape deleted successfully');
        this.getDiamondProperties();
      },
      error: (error) => {
        this.toastrService.error(error.message);
      }
    });
  }
}
