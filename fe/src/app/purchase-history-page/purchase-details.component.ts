import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-purchase-details',
  templateUrl: './purchase-details.component.html',
})
export class PurchaseDetailsComponent {
  @Input() order: any = null;

  constructor() {
  }
}
