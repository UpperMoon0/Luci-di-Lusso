import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgOtpInputModule } from  'ng-otp-input';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { RegisterPageComponent } from './register-page/register-page.component';
import { RegisterFormComponent } from './register-page/register-form.component';

import { LoginPageComponent } from './login-page/login-page.component';
import { LoginFormComponent } from './login-page/login-form.component';

import { DefaultLayoutModule } from './core/default-layout/default-layout.module';
import { DashboardModule } from './modules/dashboard/dashboard.module';
import { SharedAppModule } from './core/shared/shared.module';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './modules/home/home.component';
import { OAuthModule } from 'angular-oauth2-oidc';
import { IntroductionComponent } from './modules/default-page/introduction/introduction.component';
import {ToastrModule} from "ngx-toastr";
import { PriceDimondTableComponent } from './modules/default-page/price-dimond-table/price-dimond-table.component';
import { MeasureJewelrySizeComponent } from './modules/default-page/measure-jewelry-size/measure-jewelry-size.component';
import { AddProductComponent } from './modules/add-product/add-product.component';
import { CartComponent } from './modules/cart/cart.component';
import { UserProfileComponent } from './modules/user-profile/user-profile.component';
import {CommonModule} from "@angular/common";
import { HeaderComponent } from './modules/header/header.component';
import { FileUploadComponent } from './modules/file-upload/file-upload.component';
import { HomePageComponent } from './modules/pages/home-page/home-page.component';
import { AboutComponent } from './modules/pages/about/about.component';
import { ArticleComponent } from './modules/pages/article/article.component';
import { BlogComponent } from './modules/pages/blog/blog.component';
import { CheckoutComponent } from './modules/pages/checkout/checkout.component';
import { ContactUsComponent } from './modules/pages/contact-us/contact-us.component';
import { FaqsComponent } from './modules/pages/faqs/faqs.component';
import { MyAccountComponent } from './modules/pages/my-account/my-account.component';
import { OrderSummeryComponent } from './modules/pages/order-summery/order-summery.component';
import { PrivacyPolicyComponent } from './modules/pages/privacy-policy/privacy-policy.component';
import { ProductListComponent } from './modules/pages/product-list/product-list.component';
import { ProductComponent } from './modules/pages/product/product.component';
import { WishListComponent } from './modules/pages/wish-list/wish-list.component';
import { MyHeaderComponent } from './modules/pages/layout/my-header/my-header.component';
import { MyFooterComponent } from './modules/pages/layout/my-footer/my-footer.component';
import { MyMiniCartComponent } from './modules/pages/layout/my-mini-cart/my-mini-cart.component';
import {ResetPasswordComponent} from "./modules/pages/reset-password/reset-password.component";
import { ForgetPasswordComponent } from './modules/pages/forget-password/forget-password.component';

@NgModule({
  declarations: [
    AppComponent,

    // Register page
    RegisterPageComponent,
    RegisterFormComponent,

    // Login page
    LoginPageComponent,
    LoginFormComponent,

    //
    ResetPasswordComponent,
    HomeComponent,
    IntroductionComponent,
    PriceDimondTableComponent,
    MeasureJewelrySizeComponent,
    AddProductComponent,
    CartComponent,
    UserProfileComponent,
    HeaderComponent,
    FileUploadComponent,
    HomePageComponent,
    AboutComponent,
    ArticleComponent,
    BlogComponent,
    CheckoutComponent,
    ContactUsComponent,
    FaqsComponent,
    LoginPageComponent,
    MyAccountComponent,
    OrderSummeryComponent,
    PrivacyPolicyComponent,
    ProductListComponent,
    ProductComponent,
    WishListComponent,
    MyHeaderComponent,
    MyFooterComponent,
    MyMiniCartComponent,
    ForgetPasswordComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    DefaultLayoutModule,
    DashboardModule,
    SharedAppModule,
    BrowserAnimationsModule,
    NgbModule,
    CommonModule,
    OAuthModule.forRoot(),
    ToastrModule.forRoot({
      timeOut: 150000, // 15 seconds
      closeButton: true,
      progressBar: true,
    }),
    NgOtpInputModule


  ],
  providers: [
    // FeatureGuard,
    // {
    //   provide: LocationStrategy,
    //   useClass: HashLocationStrategy
    // },
    // {
    //   provide: HTTP_INTERCEPTORS,
    //   useClass: AuthInterceptor,
    //   multi: true,
    // },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
