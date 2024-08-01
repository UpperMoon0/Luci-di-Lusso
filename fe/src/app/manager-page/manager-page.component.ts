import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AccountService} from "../service/account.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-manager-page',
  templateUrl: './manager-page.component.html',
  styleUrls: ['./manager-page.component.css'],
})
export class ManagerPageComponent implements OnInit {

  tab: number = 2;

  constructor(private accountService: AccountService,
              private router: Router) {
  }

  public setTab(tab: number) {
    this.tab = tab;
  }

  logOut() {
    this.accountService.logout();
  }

  ngOnInit(): void {
    const token = localStorage.getItem('accessToken');
    this.accountService.validateToken(token).subscribe({
      next: (res) => {
        if (res.message == "CUSTOMER") {
          this.router.navigate(["/home"]).then(r=> {});
        }
        if (res.message == "DELIVERER") {
          this.router.navigate(["/delivery"]).then(r=> {});
        }
      }
    });
  }
}
