import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-delete-confirm',
  templateUrl: './delete-confirm.component.html',
  styleUrls: ['./delete-confirm.component.css']
})
export class DeleteConfirmComponent implements OnInit {
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
    this.data.refreshList();
    this.data.closeDialog();
  }
}
