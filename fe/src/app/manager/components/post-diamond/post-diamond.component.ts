import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Title} from "@angular/platform-browser";
import {ManagerService} from "../../services/manager.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Component({
  selector: 'app-post-diamond',
  templateUrl: './post-diamond.component.html',
  styleUrls: ['./post-diamond.component.css']
})
export class PostDiamondComponent implements OnInit{
  diamondForm!: FormGroup;
  listOfDiamondCut: any[] = [];
  listOfDiamondColor: any[] = [];
  listOfDiamondClarity: any[] = [];
  listOfDiamondShape: any[] = [];

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private managerService: ManagerService,
    private titleService: Title
  ) { }

  ngOnInit(): void {
    this.titleService.setTitle('Add Diamond | Giovanna Diamond Shop');
    this.diamondForm = this.fb.group({
      cutId: [null, [Validators.required]],
      colorId: [null, [Validators.required]],
      clarityId: [null, [Validators.required]],
      shapeId: [null, [Validators.required]],
      carat: [null, [Validators.required, Validators.min(0.01)]],
      price: [null, [Validators.required, Validators.min(1)]],
      quantity: [null, [Validators.required, Validators.min(1)]],
    });
    this.getDiamondCut();
    this.getDiamondColor();
    this.getDiamondClarity();
    this.getDiamondShape();
  }

  getDiamondCut(): void {
    this.managerService.getDiamondCut().subscribe(response => {
      this.listOfDiamondCut = response;
    });
  }

  getDiamondColor(): void {
    this.managerService.getDiamondColor().subscribe(response => {
      this.listOfDiamondColor = response;
    });
  }

  getDiamondClarity(): void {
    this.managerService.getDiamondClarity().subscribe(response => {
      this.listOfDiamondClarity = response;
    });
  }

  getDiamondShape(): void {
    this.managerService.getDiamondShape().subscribe(response => {
      this.listOfDiamondShape = response;
    });
  }

  addDiamond(){
    if(this.diamondForm.valid) {
      const formData = new FormData();
      formData.append('cutId', this.diamondForm.value.cutId);
      formData.append('colorId', this.diamondForm.value.colorId);
      formData.append('clarityId', this.diamondForm.value.clarityId);
      formData.append('shapeId', this.diamondForm.value.shapeId);
      formData.append('carat', this.diamondForm.value.carat);
      formData.append('price', this.diamondForm.value.price);
      formData.append('quantity', this.diamondForm.value.quantity);
      this.managerService.addDiamond(formData).subscribe(
        (response) => {
          this.snackBar.open('Diamond added successfully', 'Close', {
            duration: 5000,
          });
          this.router.navigateByUrl('/manager/diamond');
        },
        (error) => {
          this.snackBar.open('Failed to add diamond', 'Close', {
            duration: 5000,
          });
        }
      );
    } else {
      this.diamondForm.markAllAsTouched();
    }
  }
}
