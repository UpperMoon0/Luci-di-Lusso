import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ToastrService} from "ngx-toastr";
import {AccountService} from "../service/account.service";

@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  form!: FormGroup;

  constructor(private accountService: AccountService,
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
    this.accountService.login(this.form.value).subscribe({
      next: () => {
        this.toastrService.success("Login successfully!");
      },
      error: () => {
        this.toastrService.error("Login failed!");
      }
    });
  }
}
