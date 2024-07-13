import {Component, OnInit} from '@angular/core';
import {AccountService} from "../service/account.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-payment-page',
  templateUrl: './payment-page.component.html',
})
export class PaymentPageComponent implements OnInit {
  constructor(private accountService: AccountService,
              private router: Router) { }

  ngOnInit(): void {
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
}
