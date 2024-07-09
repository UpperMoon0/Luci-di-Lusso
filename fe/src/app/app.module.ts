import { NgModule } from '@angular/core';
import {BrowserModule, provideClientHydration} from '@angular/platform-browser';
import { NgOtpInputModule } from  'ng-otp-input';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { RegisterPageComponent } from './register-page/register-page.component';
import { RegisterFormComponent } from './register-page/register-form.component';

import { LoginPageComponent } from './login-page/login-page.component';
import { LoginFormComponent } from './login-page/login-form.component';

import { ProductListPageComponent} from "./product-list-page/product-list-page.component";

import { ProductDetailsComponent } from './product-page/product-details.component';
import { ProductPageComponent } from './product-page/product-page.component';

import { DefaultLayoutModule } from './core/default-layout/default-layout.module';
import { DashboardModule } from './modules/dashboard/dashboard.module';
import { SharedAppModule } from './core/shared/shared.module';
import {HttpClientModule, provideHttpClient, withFetch} from '@angular/common/http';
import {BrowserAnimationsModule, provideAnimations} from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './modules/home/home.component';
import { OAuthModule } from 'angular-oauth2-oidc';
import { IntroductionComponent } from './modules/default-page/introduction/introduction.component';
import { ToastrModule } from "ngx-toastr";
import { PriceDimondTableComponent } from './modules/default-page/price-dimond-table/price-dimond-table.component';
import { MeasureJewelrySizeComponent } from './modules/default-page/measure-jewelry-size/measure-jewelry-size.component';
import { AddProductComponent } from './modules/add-product/add-product.component';
import { CartComponent } from './modules/cart/cart.component';
import { UserProfileComponent } from './modules/user-profile/user-profile.component';
import {CommonModule, NgOptimizedImage} from "@angular/common";
import { FileUploadComponent } from './modules/file-upload/file-upload.component';
import { HomePageComponent } from './modules/pages/home-page/home-page.component';
import { AboutComponent } from './modules/pages/about/about.component';
import { ArticleComponent } from './modules/pages/article/article.component';
import { BlogComponent } from './components/blog/blog.component';
import { CheckoutComponent } from './modules/pages/checkout/checkout.component';
import { ContactUsComponent } from './modules/pages/contact-us/contact-us.component';
import { FaqsComponent } from './modules/pages/faqs/faqs.component';
import { MyAccountComponent } from './modules/pages/my-account/my-account.component';
import { OrderSummeryComponent } from './modules/pages/order-summery/order-summery.component';
import { PrivacyPolicyComponent } from './modules/pages/privacy-policy/privacy-policy.component';


import { WishListComponent } from './modules/pages/wish-list/wish-list.component';
import { HeaderComponent } from './header/header.component';
import { MyFooterComponent } from './modules/pages/layout/my-footer/my-footer.component';
import { MyMiniCartComponent } from './modules/pages/layout/my-mini-cart/my-mini-cart.component';
import {ResetPasswordComponent} from "./modules/pages/reset-password/reset-password.component";
import { ForgetPasswordComponent } from './modules/pages/forget-password/forget-password.component';
import { CustomerComponent } from './customer/customer.component';
import { DiscoverComponent } from './components/blog/discover/discover.component';
import { Fashion01Component } from './components/blog/fashion01/fashion01.component';
import { Fashion02Component } from './components/blog/fashion02/fashion02.component';
import { NewsComponent } from './components/blog/news/news.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatMenuModule} from "@angular/material/menu";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {DemoMaterialModule} from "./AngularMaterialModule";
import { ManagerComponent } from './manager/manager.component';
import { PostDiamondComponent } from './manager/components/post-diamond/post-diamond.component';

@NgModule({
  declarations: [
    AppComponent,

    // Header
    HeaderComponent,

    // Login page
    LoginPageComponent,
    LoginFormComponent,

    // Register page
    RegisterPageComponent,
    RegisterFormComponent,

    // Product list page
    ProductListPageComponent,

    // Product page
    ProductDetailsComponent,
    ProductPageComponent,

    //
    ResetPasswordComponent,
    HomeComponent,
    IntroductionComponent,
    PriceDimondTableComponent,
    MeasureJewelrySizeComponent,
    AddProductComponent,
    CartComponent,
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
    MyFooterComponent,
    MyMiniCartComponent,
    ForgetPasswordComponent,
    CustomerComponent,

    //Blog pages
    DiscoverComponent,
    Fashion01Component,
    Fashion02Component,
    NewsComponent,
    ManagerComponent,
    PostDiamondComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    DefaultLayoutModule,
    BrowserAnimationsModule,
    DemoMaterialModule,
    MatFormFieldModule,
    MatInputModule,
    MatSidenavModule,
    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    MatSidenavModule,
    ReactiveFormsModule,
    FormsModule,
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
    NgOptimizedImage


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
    provideClientHydration(),
    provideAnimations(),
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
