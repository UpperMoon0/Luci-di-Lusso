import {Component} from '@angular/core';
import {AccountService} from "../service/account.service";

@Component({
  selector: 'app-staff-sidebar',
  templateUrl: './staff-sidebar.component.html',
  styleUrls: ['./staff-sidebar.component.css']
})
export class StaffSidebarComponent {
  constructor(private accountService: AccountService) {
  }

  logOut() {
    this.accountService.logout();
  }
}
