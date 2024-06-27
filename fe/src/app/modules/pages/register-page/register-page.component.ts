import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountService} from "../../auth/services/account.service";
import {AuthGoogleService} from "../../../core/shared/auth-google.service";
import {ToastrService} from "ngx-toastr";
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit{
  form!: FormGroup;
  otpConfig = {
    length: 6,
    inputStyles: {
      'width': '60px',
      'height': '60px',
      'margin': '0 10px'
    },
    containerStyles: {
      'display': 'flex',
      'justify-content': 'center'
    }
  };
  isDisableButton:boolean = false;

  constructor(private http: HttpClient,
              private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private accountService: AccountService,
              private authGoogleService: AuthGoogleService,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      fullName: ['', Validators.required],
      dob: ['', Validators.required],
      provider: ['LOCAL']
    });
  }

  get f() {
    return this.form.controls;
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

    const formValue = {...this.form.value};

    this.http.post('http://localhost:8081/auth/signup', formValue).subscribe({
      next: () => {
        this.toastrService.success("Registration successful");
      },
      error: () => {
        this.toastrService.error("Registration failed");
      }
    });
  }

  callRegister() {
    const user = {
      name: this.form.controls['name'].value,
      email: this.form.controls['email'].value,
      password: this.form.controls['password'].value, //this.form['']
      province: this.form.controls['province'].value,
      district: this.form.controls['district'].value,
      city: this.form.controls['city'].value,
      ward: this.form.controls['ward'].value,
      extra: this.form.controls['extra'].value,
      phoneNumber: this.form.controls['phoneNumber'].value,
      otp: this.form.controls['otp'].value,

    };
    this.accountService.register(user).subscribe((res) => {
        this.toastrService.success("Đăng ký user thành công");
        this.isDisableButton = false;
        const returnUrl = this.route.snapshot.queryParams['/my-login'] || '/my-login';
        this.router.navigateByUrl(returnUrl);
      },
      error => {
        if (error.data) {
          this.toastrService.error(error.data);
        } else {
          this.toastrService.error("Đăng ký user thất bại");
        }

        this.isDisableButton = false;
      })
  }

  public onOpenModal( mode: string): void{
    const container = document.getElementById('main-container')!;
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'otp') {
      button.setAttribute('data-target', '#otpModal');
    }
    container.appendChild(button);
    button.click();
  }

}
