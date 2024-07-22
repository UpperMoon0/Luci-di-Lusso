import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";



@Component({
  selector: 'app-voucher-window',
  templateUrl: './voucher-window.component.html',
  styleUrls: ['./voucher-window.component.css']
})
export class VoucherWindowComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<VoucherWindowComponent>) {}

  onVoucherSelected(voucher: any): void {
    this.dialogRef.close(voucher);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
