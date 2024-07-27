import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-confirm-delete',
  templateUrl: './confirm-delete.component.html',
  styleUrls: ['./confirm-delete.component.css']
})
export class ConfirmDeleteComponent implements OnInit {
  protected entity: string = '';

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit(): void {
    this.entity = this.data.entity;
  }

  onCancel(): void {
    this.data.closeDialog();
  }

  onSubmit(): void {
    this.data.deleteEntity();
  }
}
