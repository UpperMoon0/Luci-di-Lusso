import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import { RegisterService } from './register-service';

@Component({
  selector: 'register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  form!: FormGroup;

  constructor(private registerService: RegisterService,
              private formBuilder: FormBuilder,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required],
      address: ['', Validators.required],
      fullName: ['', Validators.required],
      dob: ['', Validators.required],
      provider: ['LOCAL'] // Set default value to "LOCAL"
    });
  }

  onRegister() {
    if (this.form.invalid) {
      for (const field in this.form.controls) {
        if (this.form.controls[field].invalid) {
          this.toastrService.error(`Please check your ${field}`);
        }
      }
      return;
    }

    this.registerService.register(this.form.value).subscribe({
      next: (res) => {
        this.toastrService.success("Register successfully");
      },
      error: (err) => {
        this.toastrService.error("Register failed: " + err.message);
      }
    });
  }
}
