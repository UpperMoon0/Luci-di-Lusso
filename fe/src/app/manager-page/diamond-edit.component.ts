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
  protected diamondProperties: any = {};
  protected initialCut: any = {};
  protected initialColor: any = {};
  protected initialClarity: any = {};
  protected initialShape: any = {};

  diamondEditForm = new FormGroup({
    cut: new FormControl(this.initialCut),
    color: new FormControl(this.initialColor),
    clarity: new FormControl(this.initialClarity),
    carat: new FormControl(this.data.diamond.carat),
    shape: new FormControl(this.initialShape),
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

      // Set selected values using find
      this.initialCut = this.diamondProperties.cuts.find((cut: any) => cut.cut === this.data.diamond.cut);
      this.initialColor = this.diamondProperties.colors.find((color: any) => color.color === this.data.diamond.color);
      this.initialClarity = this.diamondProperties.clarities.find((clarity: any) => clarity.clarity === this.data.diamond.clarity);
      this.initialShape = this.diamondProperties.shapes.find((shape: any) => shape.shape === this.data.diamond.shape);

      // Update form controls with the found values
      this.diamondEditForm.patchValue({
        cut: this.initialCut,
        color: this.initialColor,
        clarity: this.initialClarity,
        shape: this.initialShape,
      });
    });
  }

  onSubmit(): void {
    console.log(this.diamondProperties);
    console.log(JSON.stringify(this.diamondEditForm.value.clarity));
    let diamond = {id: this.data.diamond.id,
                                            clarityId: this.diamondEditForm.value.clarity.id,
                                            colorId: this.diamondEditForm.value.color.id,
                                            cutId: this.diamondEditForm.value.cut.id,
                                            shapeId: this.diamondEditForm.value.shape.id,
                                            carat: this.diamondEditForm.value.carat,
                                            quantity: this.diamondEditForm.value.quantity};
    this.managerService.updateDiamond(diamond).subscribe(
      () => {
        this.toastrService.success('Diamond updated successfully');
        this.data.refreshList();
      }
    )
  }
}
