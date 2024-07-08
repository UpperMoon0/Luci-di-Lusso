import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgOtpInputModule } from  'ng-otp-input';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HeaderComponent } from './header/header.component';
import { HeaderProfileDropdownComponent } from "./header/header-profile-dropdown.component";

import { RegisterPageComponent } from './register-page/register-page.component';
import { RegisterFormComponent } from './register-page/register-form.component';

import { LoginPageComponent } from './login-page/login-page.component';
import { LoginFormComponent } from './login-page/login-form.component';

import { ProductCardComponent } from "./product-list-page/product-card.component";
import { ProductListPageComponent } from './product-list-page/product-list-page.component';

import { ProductDetailsComponent } from './product-page/product-details.component';
import { ProductPageComponent } from './product-page/product-page.component';

import { CartProductCardComponent } from "./cart-page/cart-product-card.component";
import { CartProductListComponent } from "./cart-page/cart-product-list.component";
import { CartPageComponent } from './cart-page/cart-page.component';

import { PaymentFormComponent} from "./payment-page/payment-form.component";
import { PaymentPageComponent } from './payment-page/payment-page.component';
import { NgxStripeModule } from 'ngx-stripe';
import { FormsModule } from '@angular/forms';

import { CustomerProfileFormComponent } from "./customer-profile-page/customer-profile-form.component";
import { CustomerProfilePageComponent } from './customer-profile-page/customer-profile-page.component';

import { DefaultLayoutModule } from './core/default-layout/default-layout.module';
import { DashboardModule } from './modules/dashboard/dashboard.module';
import { SharedAppModule } from './core/shared/shared.module';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './modules/home/home.component';
import { OAuthModule } from 'angular-oauth2-oidc';
import { IntroductionComponent } from './modules/default-page/introduction/introduction.component';
import { ToastrModule } from "ngx-toastr";
import { PriceDimondTableComponent } from './modules/default-page/price-dimond-table/price-dimond-table.component';
import { MeasureJewelrySizeComponent } from './modules/default-page/measure-jewelry-size/measure-jewelry-size.component';

import { UserProfileComponent } from './modules/user-profile/user-profile.component';
import {CommonModule, NgOptimizedImage} from "@angular/common";
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
import { WishListComponent } from './modules/pages/wish-list/wish-list.component';

import { FooterComponent } from './footer/footer.component';
import {ResetPasswordComponent} from "./modules/pages/reset-password/reset-password.component";
import { ForgetPasswordComponent } from './modules/pages/forget-password/forget-password.component';


@NgModule({
  declarations: [
    AppComponent,

    // Header
    HeaderProfileDropdownComponent,
    HeaderComponent,

    // Login page
    LoginFormComponent,
    LoginPageComponent,

    // Register page
    RegisterFormComponent,
    RegisterPageComponent,

    // Product list page
    ProductCardComponent,
    ProductListPageComponent,

    // Product page
    ProductDetailsComponent,
    ProductPageComponent,

    // Cart page
    CartProductCardComponent,
    CartProductListComponent,
    CartPageComponent,

    // Payment page
    PaymentFormComponent,
    PaymentPageComponent,

    // Customer profile page
    CustomerProfileFormComponent,
    CustomerProfilePageComponent,

    //
    ResetPasswordComponent,
    HomeComponent,
    IntroductionComponent,
    PriceDimondTableComponent,
    MeasureJewelrySizeComponent,

    UserProfileComponent,
    FileUploadComponent,
    HomePageComponent,
    AboutComponent,
    ArticleComponent,
    BlogComponent,
    CheckoutComponent,
    ContactUsComponent,
    FaqsComponent,
    MyAccountComponent,
    OrderSummeryComponent,
    PrivacyPolicyComponent,
    WishListComponent,
    FooterComponent,
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
    NgOtpInputModule,
    NgOptimizedImage,
    NgxStripeModule.forRoot('pk_test_51PZaB3GvGs3G9mvhzYptSjAz1VvSJwDqt6ZVs1YTi0kU4cMSX5GH0Qms3rQaP5oLqKJgDk17gH9O8PYbEjAfkjbD00qqxTZ7nl'),
    FormsModule,
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
