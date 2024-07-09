import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManagerComponent } from './manager.component';
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {PostCouponComponent} from "./components/post-coupon/post-coupon.component";
import {CouponsComponent} from "./components/coupons/coupons.component";
import {PostDiamondComponent} from "./components/post-diamond/post-diamond.component";

const routes: Routes = [
  { path: '', component: ManagerComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'diamond', component: PostDiamondComponent },
  { path: 'jewelry', component: PostDiamondComponent },
  { path: 'post-coupon', component: PostCouponComponent },
  { path: 'coupons', component: CouponsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManagerRoutingModule { }
