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
import {PurchaseHistoryPageComponent} from "./purchase-history-page/purchase-history-page.component";
import {ManagerPageComponent} from "./manager-page/manager-page.component";
import {BlogPageComponent} from "./blog-page/blog/blog-page.component";
import {NewsComponent} from "./blog-page/blog/news/news.component";
import {DiscoverComponent} from "./blog-page/blog/discover/discover.component";
import {Fashion02Component} from "./blog-page/blog/fashion02/fashion02.component";
import {Fashion01Component} from "./blog-page/blog/fashion01/fashion01.component";
import {CertificatePageComponent} from "./certificate-page/certificate-page.component";
import {WarrantyPageComponent} from "./warranty/warranty-page.component";
import {CollectionListPageComponent} from "./collection-page/collection-list-page.component";
import {CollectionPageComponent} from "./collection-page/collection-page.component";

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
  { path: 'manager', component: ManagerPageComponent },
  { path: 'purchase-history', component: PurchaseHistoryPageComponent },
  { path: 'manager', component : ManagerPageComponent},
  { path : 'blog', component : BlogPageComponent },

  {
    path: 'top-jewelry-trend-for-2024',
    component: Fashion01Component,
    data: {
      title: 'Top Jewelry Trend For 2024'
    }
  },

  {
    path: 'top-jewelry-trend-for-2024',
    component: Fashion02Component,
    data: {
      title: 'Top Jewelry Trend For 2024'
    }
  },

  {
    path: 'bracelet-and-ring-size-guide',
    component: DiscoverComponent,
    data: {
      title: 'Bracelet And Ring Size Guide'
    }
  },

  {
    path: 'diamond-prices',
    component: NewsComponent,
    data: {
      title: 'Diamond Prices Chart to Calculate 2024 Worth & Value'
    }
  },

  {
    path: 'certificate/:id',
    component: CertificatePageComponent,
  },

  {
    path: 'warranty/:id',
    component: WarrantyPageComponent,
  },

  {
    path: 'collection-list',
    component: CollectionListPageComponent,
  },

  {
    path: 'collection/:id',
    component: CollectionPageComponent,
  },

  { path: '**', redirectTo: 'home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
