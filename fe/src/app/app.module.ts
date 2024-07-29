import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgOtpInputModule } from  'ng-otp-input';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HeaderComponent } from './header/header.component';
import { HeaderProfileDropdownComponent } from "./header/header-profile-dropdown.component";
import { HeaderCartComponent } from "./header/header-cart.component";

import { FooterComponent } from './footer/footer.component';

import { HomePageComponent } from './home-page/home-page.component';

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
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { CustomerProfileFormComponent } from "./customer-profile-page/customer-profile-form.component";
import { CustomerProfilePageComponent } from './customer-profile-page/customer-profile-page.component';

import { DeliveryTableComponent } from './delivery-page/delivery-table.component';
import { DeliveryPageComponent } from './delivery-page/delivery-page.component';

import {ManagerDiamondListComponent} from "./manager-page/diamond/manager-diamond-list.component";
import {ManagerPageComponent} from "./manager-page/manager-page.component";

import {DiscoverComponent} from "./blog-page/blog/discover/discover.component";
import {Fashion01Component} from "./blog-page/blog/fashion01/fashion01.component";
import {Fashion02Component} from "./blog-page/blog/fashion02/fashion02.component";
import {NewsComponent} from "./blog-page/blog/news/news.component";
import {BlogPageComponent} from "./blog-page/blog/blog-page.component";

import {PurchaseDetailsComponent} from "./purchase-history-page/purchase-details.component";
import {PurchaseHistoryListComponent} from "./purchase-history-page/purchase-history-list.component";
import {PurchaseHistoryPageComponent} from "./purchase-history-page/purchase-history-page.component";

import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {CommonModule, NgOptimizedImage} from "@angular/common";
import {ToastrModule} from "ngx-toastr";
import {OAuthModule} from "angular-oauth2-oidc";
import { HomeBlogComponent } from './home-page/home-blog.component';
import {HomeProductRowComponent} from "./home-page/home-product-row.component";
import {MatInputModule} from "@angular/material/input";
import {DemoMaterialModule} from "./AngularMaterialModule";
import { ManagerDeliveryListComponent } from './manager-page/delivery/manager-delivery-list.component';
import { StatisticsComponent } from './manager-page/statistics.component';
import {CanvasJSAngularChartsModule} from "@canvasjs/angular-charts";
import { ManagerJewelryListComponent } from './manager-page/jewelry/manager-jewelry-list.component';
import {CertificatePageComponent} from "./certificate-page/certificate-page.component";
import { HomeBannerComponent} from "./home-page/home-banner.component";
import {ImageSliderModule} from "./image-slider/image-slider.module";
import {DiamondEditFormComponent} from "./manager-page/diamond/diamond-edit-form.component";
import {MatCardModule} from "@angular/material/card";
import {MatTableModule} from "@angular/material/table";
import {MatButtonModule} from "@angular/material/button";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {WarrantyPageComponent} from "./warranty-page/warranty-page.component";
import {JewelryEditFormComponent} from "./manager-page/jewelry/jewelry-edit-form.component";
import {DeliveryDetailsComponent} from "./manager-page/delivery/delivery-details.component";
import {DeliverySidebarComponent} from "./delivery-page/delivery-sidebar.component";
import {CollectionListPageComponent} from "./collection-page/collection-list-page.component";
import {CollectionCardComponent} from "./collection-page/collection-card.component";
import {CollectionPageComponent} from "./collection-page/collection-page.component";
import {HomeCollectionRowComponent} from "./home-page/home-collection-row.component";
import {ManagerVoucherListComponent} from "./manager-page/voucher/manager-voucher-list.component";
import { MyVouchersPageComponent } from './my-vouchers-page/my-vouchers-page.component';
import { VouchersListComponent } from './my-vouchers-page/vouchers-list.component';
import {EditFormComponent} from "./manager-page/edit-form.component";
import {CompleteDeliveryConfirmComponent} from "./delivery-page/complete-delivery-confirm.component";

@NgModule({
  declarations: [
    AppComponent,

    // Header
    HeaderProfileDropdownComponent,
    HeaderCartComponent,
    HeaderComponent,

    // Footer
    FooterComponent,

    // Home page
    HomeCollectionRowComponent,
    HomeProductRowComponent,
    HomePageComponent,
    HomeBannerComponent,
    HomeBlogComponent,

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

    // Delivery page
    CompleteDeliveryConfirmComponent,
    DeliverySidebarComponent,
    DeliveryTableComponent,
    DeliveryPageComponent,

    // Manager page
    EditFormComponent,
    StatisticsComponent,
    DiamondEditFormComponent,
    ManagerDeliveryListComponent,
    JewelryEditFormComponent,
    ManagerJewelryListComponent,
    ManagerVoucherListComponent,
    DeliveryDetailsComponent,
    ManagerDiamondListComponent,
    ManagerPageComponent,

    // Blog page
    DiscoverComponent,
    Fashion01Component,
    Fashion02Component,
    NewsComponent,
    BlogPageComponent,

    // Purchase history page
    PurchaseDetailsComponent,
    PurchaseHistoryListComponent,
    PurchaseHistoryPageComponent,

    // Certificate page
    CertificatePageComponent,

    // Warranty page
    WarrantyPageComponent,

    // Collection list page
    CollectionCardComponent,
    CollectionListPageComponent,

    // Collection page
    CollectionPageComponent,

    MyVouchersPageComponent,
    VouchersListComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    CommonModule,
    OAuthModule.forRoot(),
    ToastrModule.forRoot({
      timeOut: 150000,
      closeButton: true,
      progressBar: true,
    }),
    NgOtpInputModule,
    NgOptimizedImage,
    NgxStripeModule.forRoot('pk_test_51PZaB3GvGs3G9mvhzYptSjAz1VvSJwDqt6ZVs1YTi0kU4cMSX5GH0Qms3rQaP5oLqKJgDk17gH9O8PYbEjAfkjbD00qqxTZ7nl'),
    FormsModule,
    ReactiveFormsModule,
    DemoMaterialModule,
    CanvasJSAngularChartsModule,
    ImageSliderModule,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatSnackBarModule,
    MatInputModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
