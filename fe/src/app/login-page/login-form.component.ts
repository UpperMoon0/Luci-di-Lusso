import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from "./login-service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  form!: FormGroup;

  constructor(private loginService: LoginService,
              private formBuilder: FormBuilder,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      principal: ['', Validators.required],
      credentials: ['', Validators.required],
    });
  }

  onLogin() {
    if (this.form.invalid) {
      for (const field in this.form.controls) {
        if (this.form.controls[field].invalid) {
          this.toastrService.error(`Please check your ${field}`);
        }
      }
      return;
    }

    this.loginService.login(this.form.value).subscribe({
      next: (res) => {
        this.toastrService.success("Login successfully");
      },
      error: (err) => {
        this.toastrService.error("Login failed: " + err.message);
      }
    });
  }
}
