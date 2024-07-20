import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Title } from "@angular/platform-browser";
import { ManagerService } from "../service/manager.service";
import { MatSnackBar } from "@angular/material/snack-bar";
import { MatDialog } from "@angular/material/dialog";
import { DiamondEditComponent } from "./diamond-edit.component";

@Component({
  selector: 'app-diamond-list',
  templateUrl: './manage-diamond-list.component.html',
  styleUrls: ['./manage-diamond-list.component.css']
})
export class ManageDiamondListComponent implements OnInit {
  diamonds: { id: number, color: string, clarity: string, cut: string, shape: string, carat: number, quantity: number }[] = [];
  displayedColumns = ['id', 'color', 'clarity', 'cut', 'shape', 'carat', 'quantity', 'actions'];

  constructor(private managerService: ManagerService,
              private titleService: Title,
              private snackBar: MatSnackBar,
              public dialog: MatDialog) {}

  ngOnInit() {
    this.getDiamonds();
    this.titleService.setTitle('Diamond List | Luci di Lusso');
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

  deleteDiamond(diamondId: any) {
    this.managerService.deleteDiamond(diamondId).subscribe(response => {
      if (response == null) {
        if (typeof window !== 'undefined') {
          localStorage.setItem('showSnackbar', 'true');
        }
        window.location.reload();
      } else {
        this.snackBar.open(response.message, 'Close', {
          duration: 5000,
          panelClass: 'error-snackbar'
        });
      }
    });
  }

  openEditDiamondDialog(diamond: any): void {
    const dialogRef = this.dialog.open(DiamondEditComponent, {
      data: { diamond: diamond },
      width: '400px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
