import { Component } from '@angular/core';

@Component({
  selector: 'app-manager-page',
  templateUrl: './manager-page.component.html',
  styleUrls: ['./manager-page.component.css']
})
export class ManagerPageComponent {

  tab: number = 0;

  public setTab(tab: number) {
    this.tab = tab;
  }

}
