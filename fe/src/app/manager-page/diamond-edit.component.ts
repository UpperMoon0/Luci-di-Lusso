import {Component, Inject, OnInit} from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ProductService} from "../service/product.service";
import {ManagerService} from "../service/manager.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-diamond-edit',
  templateUrl: './diamond-edit.component.html',
  styleUrls: ['./diamond-edit.component.css']
})
export class DiamondEditComponent implements OnInit {
  protected diamondProperties: any;
  protected selectedCut: any = '';
  protected selectedColor: any = '';
  protected selectedClarity: any = '';
  protected selectedShape: any = '';

  diamondEditForm = new FormGroup({
    cut: new FormControl(this.selectedCut.id),
    color: new FormControl(this.selectedColor.id),
    clarity: new FormControl(this.selectedClarity.id),
    carat: new FormControl(this.data.diamond.carat),
    shape: new FormControl(this.selectedShape.id),
    quantity: new FormControl(this.data.diamond.quantity),
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private productService: ProductService,
    private managerService: ManagerService,
    private toastrService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.productService.getAllDiamondProperties().subscribe(response => {
      this.diamondProperties = response;

      // Set selected values
      this.diamondProperties.cuts.forEach((cut: any) => {
        if (cut.cut === this.data.diamond.cut) {
          this.selectedCut = cut;
        }
      });

      this.diamondProperties.colors.forEach((color: any) => {
        if (color.color === this.data.diamond.color) {
          this.selectedColor = color;
        }
      });

      this.diamondProperties.clarities.forEach((clarity: any) => {
        if (clarity.clarity === this.data.diamond.clarity) {
          this.selectedClarity = clarity;
        }
      });

      this.diamondProperties.shapes.forEach((shape: any) => {
        if (shape.shape === this.data.diamond.shape) {
          this.selectedShape = shape;
        }
      });
    });
  }

  onSubmit(): void {
    let diamond = {id: this.data.diamond.id, clarityId: this.selectedClarity.id, colorId: this.selectedColor.id, cutId: this.selectedCut.id, shapeId: this.selectedShape.id, carat: this.diamondEditForm.value.carat, quantity: this.diamondEditForm.value.quantity};
    this.managerService.updateDiamond(diamond).subscribe(
      () => {
        this.toastrService.success('Diamond updated successfully');
        this.data.refreshList();
      }
    )
  }
}
