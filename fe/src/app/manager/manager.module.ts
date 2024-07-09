import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ManagerRoutingModule } from './manager-routing.module';
import { ManagerComponent } from './manager.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatToolbar, MatToolbarModule} from "@angular/material/toolbar";
import {MatButton, MatButtonModule} from "@angular/material/button";
import {MatMenuModule} from "@angular/material/menu";
import { PostCouponComponent } from './components/post-coupon/post-coupon.component';
import { CouponsComponent } from './components/coupons/coupons.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatMomentDateModule} from "@angular/material-moment-adapter";
import {DemoMaterialModule} from "../AngularMaterialModule";
import {PostDiamondComponent} from "./components/post-diamond/post-diamond.component";
import { PostJewelryComponent } from './components/post-jewelry/post-jewelry.component';


@NgModule({
  declarations: [
    ManagerComponent,
    DashboardComponent,
    PostDiamondComponent,
    PostJewelryComponent,
    PostCouponComponent,
    CouponsComponent,
    PostJewelryComponent
  ],
  imports: [
    CommonModule,
    ManagerRoutingModule,
    HttpClientModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    ReactiveFormsModule,
    DemoMaterialModule,
    MatDatepickerModule,
    MatMomentDateModule
  ]
})
export class ManagerModule { }
