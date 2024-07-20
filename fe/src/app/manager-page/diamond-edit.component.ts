import {Component, Inject, OnInit} from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ProductService} from "../service/product.service";

@Component({
  selector: 'app-diamond-edit',
  templateUrl: './diamond-edit.component.html',
  styleUrls: ['./diamond-edit.component.css']
})
export class DiamondEditComponent implements OnInit {
  protected diamondProperties: any;

  diamondEditForm = new FormGroup({
    cut: new FormControl(''),
    color: new FormControl(''),
    clarity: new FormControl(''),
    carat: new FormControl(''),
    shape: new FormControl(''),
    quantity: new FormControl(''),
  });

  constructor(
    public dialogRef: MatDialogRef<DiamondEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private productService: ProductService
  ) { }

  ngOnInit(): void {
    this.productService.getAllDiamondProperties().subscribe(response => {
      this.diamondProperties = response;
    });

    this.initializeFormWithPassedData();
  }

  initializeFormWithPassedData(): void {
  if (this.data.diamond && this.diamondProperties) {
    const selectedCut = this.diamondProperties.cuts.find((cut: { cut: string; }) => cut.cut === this.data.diamond.cut) || '';
    const selectedColor = this.diamondProperties.colors.find((color: { color: string; }) => color.color === this.data.diamond.color) || '';
    const selectedClarity = this.diamondProperties.clarities.find((clarity: { clarity: string; }) => clarity.clarity === this.data.diamond.clarity) || '';
    const selectedShape = this.diamondProperties.shapes.find((shape: { shape: string; }) => shape.shape === this.data.diamond.shape) || '';

    this.diamondEditForm.setValue({
      cut: selectedCut,
      color: selectedColor,
      clarity: selectedClarity,
      shape: selectedShape,
      carat: this.data.diamond.carat || '',
      quantity: this.data.diamond.quantity || '',
    });
  }
}

  onSubmit(): void {
    console.log(this.diamondEditForm.value);
  }

  closeDialog(): void {
    this.dialogRef.close();
  }
}
