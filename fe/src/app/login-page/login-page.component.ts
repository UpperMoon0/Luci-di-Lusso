import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountService} from "../modules/auth/services/account.service";
import {AuthGoogleService} from "../core/shared/auth-google.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginPageComponent {
}
