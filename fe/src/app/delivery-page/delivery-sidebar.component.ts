import {Component} from '@angular/core';
import {AccountService} from "../service/account.service";

@Component({
  selector: 'app-delivery-sidebar',
  templateUrl: './delivery-sidebar.component.html',
  styleUrls: ['./delivery-sidebar.component.css']
})
export class DeliverySidebarComponent {
  constructor(private accountService: AccountService) {
  }

  logOut() {
    this.accountService.logout();
  }
}
