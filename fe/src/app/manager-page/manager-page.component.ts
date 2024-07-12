import { Component } from '@angular/core';
import {AccountService} from "../service/account.service";

@Component({
  selector: 'app-manager-page',
  templateUrl: './manager-page.component.html',
  styleUrls: ['./manager-page.component.css']
})
export class ManagerPageComponent {

  tab: number = 2;

  constructor(private accountService: AccountService) {
  }

  public setTab(tab: number) {
    this.tab = tab;
  }

  logOut() {
    this.accountService.logout();
  }

}
