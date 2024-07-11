import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {HomePageComponent} from "./home-page/home-page.component";
import { LoginPageComponent} from "./login-page/login-page.component";
import { RegisterPageComponent } from "./register-page/register-page.component";
import { ProductListPageComponent } from "./product-list-page/product-list-page.component";
import { ProductPageComponent } from "./product-page/product-page.component";
import { CartPageComponent } from "./cart-page/cart-page.component";
import { PaymentPageComponent } from "./payment-page/payment-page.component";
import {CustomerProfilePageComponent} from "./customer-profile-page/customer-profile-page.component";
import {DeliveryPageComponent} from "./delivery-page/delivery-page.component";
import {ManagerPageComponent} from "./manager-page/manager-page.component";
import {BlogPageComponent} from "./blog-page/blog/blog-page.component";

const routes: Routes = [
  { path: 'home', component: HomePageComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'product-list', component: ProductListPageComponent },
  { path: 'product/:id', component: ProductPageComponent },
  { path: 'cart', component: CartPageComponent },
  { path: 'payment', component: PaymentPageComponent },
  { path: 'profile', component: CustomerProfilePageComponent },
  { path: 'delivery', component: DeliveryPageComponent },
  { path: 'manager', component : ManagerPageComponent},
  { path : 'blog', component : BlogPageComponent },

  { path: '**', redirectTo: 'home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
