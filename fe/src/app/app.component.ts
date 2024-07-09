import {Component, OnInit, ViewChild} from '@angular/core';
import { Title } from "@angular/platform-browser";
import {StorageService} from "./service/storage.service";
import {Router} from "@angular/router";
import {MatSidenav} from "@angular/material/sidenav";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'lte-angular';
  isManagerLoggedIn: boolean;
  @ViewChild('sidenav') sidenav: MatSidenav;

  constructor(
    private router: Router,
    private titleService: Title,
    private storageService: StorageService
  ) {
  }

  ngOnInit(): void {
    this.titleService.setTitle('Manager Dashboard');
    this.router.events.subscribe(event => {
      this.isManagerLoggedIn = this.storageService.isManagerLoggedIn();
    })
  }

  logout() {
    StorageService.signOut();
    this.router.navigateByUrl('/home-page');
  }
}
