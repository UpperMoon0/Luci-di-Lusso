import {Component, Inject, OnInit} from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {ToastrService} from "ngx-toastr";
import {DiamondService} from "../service/diamond.service";

@Component({
  selector: 'app-diamond-edit-form',
  templateUrl: './diamond-edit-form.component.html',
  styleUrls: ['./diamond-edit-form.component.css']
})
export class DiamondEditFormComponent implements OnInit {
  protected diamondProperties: any = {};
  private diamondId: number = 0;

  diamondEditForm = new FormGroup({
    cut: new FormControl(),
    color: new FormControl(),
    clarity: new FormControl(),
    carat: new FormControl(),
    shape: new FormControl(),
    quantity: new FormControl(),
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private diamondService: DiamondService,
    private toastrService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.diamondService.getAllDiamondProperties().subscribe(response => {
      this.diamondProperties = response;

      let initialCut = this.diamondProperties.cuts[0];
      let initialColor = this.diamondProperties.colors[0];
      let initialClarity = this.diamondProperties.clarities[0];
      let initialShape = this.diamondProperties.shapes[0];
      let initialCarat = 1;
      let initialQuantity = 100;

      // Set selected values using find
      if (this.data.diamond) {
        this.diamondId = this.data.diamond.id;
        initialCut = this.diamondProperties.cuts.find((cut: any) => cut.cut === this.data.diamond.cut);
        initialColor = this.diamondProperties.colors.find((color: any) => color.color === this.data.diamond.color);
        initialClarity = this.diamondProperties.clarities.find((clarity: any) => clarity.clarity === this.data.diamond.clarity);
        initialShape = this.diamondProperties.shapes.find((shape: any) => shape.shape === this.data.diamond.shape);
        initialCarat = this.data.diamond.carat;
        initialQuantity = this.data.diamond.quantity;
      }

      // Update form controls with the found values
      this.diamondEditForm.patchValue({
        cut: initialCut,
        color: initialColor,
        clarity: initialClarity,
        shape: initialShape,
        carat: initialCarat,
        quantity: initialQuantity
      });
    });
  }

  save(): void {
    let diamond = {id: this.diamondId,
                                            clarityId: this.diamondEditForm.value.clarity.id,
                                            colorId: this.diamondEditForm.value.color.id,
                                            cutId: this.diamondEditForm.value.cut.id,
                                            shapeId: this.diamondEditForm.value.shape.id,
                                            carat: this.diamondEditForm.value.carat,
                                            quantity: this.diamondEditForm.value.quantity};
    this.diamondService.saveDiamond(diamond).subscribe({
      next: () => {
        this.toastrService.success('Diamond updated');
        this.data.refreshList();
      },
      error: () => this.toastrService.error('Error updating diamond')
    });
  }
}
