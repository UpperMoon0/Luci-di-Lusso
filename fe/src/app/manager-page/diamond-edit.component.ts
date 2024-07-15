import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-diamond-edit',
  templateUrl: './diamond-edit.component.html'//,
  //styleUrls: ['./diamond-edit.component.css']
})
export class DiamondEditComponent implements OnInit {
  diamondEditForm = new FormGroup({
    cut: new FormControl(''),
    color: new FormControl(''),
    clarity: new FormControl(''),
    carat: new FormControl(''),
    shape: new FormControl(''),
  });

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    // Here you can handle the form submission.
    // For example, you can log the form values to the console:
    console.log(this.diamondEditForm.value);
  }
}
