import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { JewelryService } from '../service/jewelry.service';
import { ManagerService } from '../service/manager.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.css']
})
export class EditFormComponent implements OnInit {
  editForm: FormGroup;
  formConfig: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit(): void {
    this.formConfig = {
      fields: this.data.fields.map((field: any) => ({
        name: field.name,
        label: field.label,
        type: field.type,
        value: field.value,
      }))
    };

    let formGroup = {};
    this.formConfig.fields.forEach((field: any) => {
      if (field.type !== 'array') {
        formGroup[field.name] = new FormControl(field.value);
      } else {
        formGroup[field.name] = new FormControl(field.value.initValue);
      }
    });

    this.editForm = new FormGroup(formGroup);
  }

  save(): void {
    // Create new object with only the values of the form
    let obj = {};
    this.formConfig.fields.forEach((field: any) => {
      obj[field.name] = this.editForm.value[field.name];
    });

    this.data.save(obj);
  }
}
