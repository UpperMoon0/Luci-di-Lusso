import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProductService } from '../service/product.service';
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
    private productService: ProductService,
    private managerService: ManagerService,
    private toastrService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.formConfig = {
      fields: this.data.fields.map((field: any) => ({
        name: field.name,
        type: field.type,
        value: field.value,
        ...(field.displayedName && { displayedName: field.displayedName })
      }))
    };

    let formGroup = {};
    this.formConfig.fields.forEach((field: any) => {
      formGroup[field.name] = new FormControl(field.value);
    });

    this.editForm = new FormGroup(formGroup);
  }

  onSubmit(): void {
    // Handle form submission
  }
}
