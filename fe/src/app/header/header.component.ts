import { Component, OnInit } from '@angular/core';
import {AccountService} from "../service/account-service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {
  isLoggedIn: boolean;

  constructor(private accountService: AccountService) { }

  ngOnInit() {
    this.accountService.isLoggedIn.subscribe(value => {
      this.isLoggedIn = value;
    });
  }

  logOut() {
    this.accountService.isLoggedIn.next(false);
  }
}
