import {Component} from '@angular/core';

@Component({
  selector: 'app-delivery-page',
  templateUrl: './delivery-page.component.html',
  styleUrls: ['./delivery-page.component.css']
})
export class DeliveryPageComponent {

  willOpened : boolean = false;

  public modifyOpen(status : number) {
    if (status === 0){
      this.willOpened = false;
    } else {
      this.willOpened = true;
    }
  }

}
