import {Component, OnInit, ViewChild} from '@angular/core';
import { RegisterFormComponent } from './register-form.component';

@Component({
  selector: 'register-page',
  templateUrl: './register-page.component.html',
})
export class RegisterPageComponent implements OnInit{
  @ViewChild(RegisterFormComponent) registerFormComponent!: RegisterFormComponent;

  constructor() {
  }

  ngOnInit(): void {
  }
}
