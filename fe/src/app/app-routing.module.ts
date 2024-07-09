import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomePageComponent} from "./modules/pages/home-page/home-page.component";
import { LoginPageComponent} from "./login-page/login-page.component";
import { RegisterPageComponent } from "./register-page/register-page.component";
import { ProductListPageComponent } from "./product-list-page/product-list-page.component";
import { ProductPageComponent } from "./product-page/product-page.component";
import { CartPageComponent } from "./cart-page/cart-page.component";
import { PaymentPageComponent } from "./payment-page/payment-page.component";
import {CustomerProfilePageComponent} from "./customer-profile-page/customer-profile-page.component";
import {DeliveryDashboardComponent} from "./delivery-dashboard/delivery-dashboard.component";

const routes: Routes = [
  { path: 'home', component: HomePageComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'product-list', component: ProductListPageComponent },
  { path: 'product/:id', component: ProductPageComponent },
  { path: 'cart', component: CartPageComponent },
  { path: 'payment', component: PaymentPageComponent },
  { path: 'profile', component: CustomerProfilePageComponent },
  { path: 'delivery-dashboard', component: DeliveryDashboardComponent},

  { path: '**', redirectTo: 'home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
