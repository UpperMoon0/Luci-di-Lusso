import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AccountService} from "../service/account.service";

@Component({
  selector: 'app-purchase-history-page',
  templateUrl: './purchase-history-page.component.html',
})
export class PurchaseHistoryPageComponent implements OnInit {
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
