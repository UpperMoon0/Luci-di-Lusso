import {Component, OnInit} from '@angular/core';
import {AccountService} from "../service/account.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
})
export class ProductPageComponent implements OnInit {
  constructor(private accountService: AccountService,
              private router: Router) { }

  ngOnInit(): void {
    let token = localStorage.getItem('accessToken');
    if (token == null) {
      token = "noToken";
    }
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
}
