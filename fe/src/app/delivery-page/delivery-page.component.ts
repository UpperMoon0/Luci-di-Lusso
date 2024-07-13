import {Component} from '@angular/core';

@Component({
  selector: 'app-delivery-page',
  templateUrl: './delivery-page.component.html',
  styleUrls: ['./delivery-page.component.css']
})
export class DeliveryPageComponent {

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

}
