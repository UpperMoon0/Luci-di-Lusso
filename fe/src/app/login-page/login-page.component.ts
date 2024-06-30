import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountService} from "../modules/auth/services/account.service";
import {AuthGoogleService} from "../core/shared/auth-google.service";
import {ToastrService} from "ngx-toastr";
import {first} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-my-login',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginPageComponent {
}
