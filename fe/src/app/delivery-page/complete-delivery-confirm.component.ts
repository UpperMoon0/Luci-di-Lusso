import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-complete-delivery-confirm',
  templateUrl: './complete-delivery-confirm.component.html',
  styleUrls: ['./complete-delivery-confirm.component.css']
})
export class CompleteDeliveryConfirmComponent implements OnInit {
  private dialogRef: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit(): void {
    this.dialogRef = this.data.dialogRef;
  }

  cancel() {
    this.dialogRef.closeAll();
  }
}
