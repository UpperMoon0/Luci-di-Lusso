import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AccountService} from "../service/account.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {
  isLoginUser:boolean = false;
  listProduct:any[]

  constructor(private route: ActivatedRoute,
              private router: Router,
              private accountService: AccountService) {
  }

  ngOnInit(): void {
    this.isLoginUser = localStorage.getItem("user") != null;
    this.getProducts();
    console.log(localStorage.getItem("user"),this.isLoginUser);
    const token = localStorage.getItem('accessToken');
    this.accountService.validateToken(token).subscribe({
      next: (res) => {
        if (res.message == "MANAGER") {
          this.router.navigate(["/manager"]).then(r=> {});
        }
        if (res.message == "DELIVERER") {
          this.router.navigate(["/delivery"]).then(r=> {});
        }
      }
    });
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
