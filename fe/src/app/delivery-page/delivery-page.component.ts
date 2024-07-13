import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AccountService} from "../service/account.service";

@Component({
  selector: 'app-delivery-page',
  templateUrl: './delivery-page.component.html',
  styleUrls: ['./delivery-page.component.css']
})
export class DeliveryPageComponent implements OnInit{

  constructor(private accountService: AccountService,
              private router: Router) {}

  choseDeliveryID: number;

  willOpened : boolean = false;

  public modifyOpen(status : number) {
    this.willOpened = status !== 0;
  }

  public setChoseDeliveryID(id : number) {
    this.choseDeliveryID = id;
  }

  public getChoseDeliveryID() {
    return this.choseDeliveryID;
  }

  ngOnInit(): void {
    const token = localStorage.getItem('accessToken');
    this.accountService.validateToken(token).subscribe({
      next: (res) => {
        if (res.message == "MANAGER") {
          this.router.navigate(["/manager"]).then(r=> {});
        }
        if (res.message == "CUSTOMER") {
          this.router.navigate(["/home"]).then(r=> {});
        }
      }
    });
  }
}
