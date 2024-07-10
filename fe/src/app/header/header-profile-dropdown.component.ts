import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { UserService } from "../service/user.service";
import {AccountService} from "../service/account.service";

@Component({
  selector: 'app-header-profile-dropdown',
  templateUrl: './header-profile-dropdown.component.html',
})
export class HeaderProfileDropdownComponent implements OnInit, OnDestroy {
  fullName: string;
  imageUrl: string;

  private profileSubscription: Subscription;

  constructor(private userService: UserService,
              private accountService: AccountService) {}

  ngOnInit() {
    this.profileSubscription = this.userService.getProfileData().subscribe(profileData => {
      if (profileData) {
        this.fullName = profileData.fullName;
        this.imageUrl = profileData.imageUrl;
      }
    });
  }

  ngOnDestroy() {
    if (this.profileSubscription) {
      this.profileSubscription.unsubscribe();
    }
  }

  logOut() {
    this.accountService.logout();
  }

  handleImageError(event) {
    event.target.src = 'assets/images/default_avatar.png';
  }
}
