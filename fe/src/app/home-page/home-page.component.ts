import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {
  isLoginUser:boolean = false;
  listProduct:any[]

  constructor(private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.isLoginUser = localStorage.getItem("user") != null;
    this.getProducts();
    console.log(localStorage.getItem("user"),this.isLoginUser);
  }

  redirectLogin() {
    const returnUrl = this.route.snapshot.queryParams['/login-v3'] || '/login-v3';
    this.router.navigateByUrl(returnUrl);

  }
  redirectProfile() {
    const returnUrl = this.route.snapshot.queryParams['/user-profile'] || '/user-profile';
    this.router.navigateByUrl(returnUrl);

  }

  goToProductDetail(id:number) {
    console.log(id);
    const returnUrl = this.route.snapshot.queryParams[`/ecommerce?id=${id}`] || `/ecommerce?id=${id}`;
    this.router.navigateByUrl(returnUrl);
  }

  getProducts() {

  }

}
