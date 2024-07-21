import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {OrderService} from "../service/order.service";
import {AccountService} from "../service/account.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-warranty-page',
  templateUrl: './warranty-page.component.html',
})
export class WarrantyPageComponent implements OnInit {
  protected orderItem: any;

  constructor(private orderService: OrderService,
              private accountService: AccountService,
              private router: Router,
              private route: ActivatedRoute,
              private toastrService: ToastrService) { }

  ngOnInit(): void {
    this.getOrderItem(+this.route.snapshot.paramMap.get('id'));

    const token = localStorage.getItem('accessToken');
    this.accountService.validateToken(token).subscribe({
      next: (res) => {
        if (res.message == "MANAGER") {
          this.router.navigate(["/manager"]).then(r=> {});
        }
        if (res.message == "DELIVERER") {
          this.router.navigate(["/delivery"]).then(r=> {});
        }
      }
    });
  }

  public getOrderItem(id: number): void {
    this.orderService.getOrderItem(id).subscribe({
      next: (orderItem: any) => {
        this.orderItem = orderItem;
      },
      error: () => this.toastrService.error("Failed to fetch order item"),
    });
  }

  getWarrantyExpirationDate(createAt: string): string {
    const createDate = new Date(createAt);
    createDate.setFullYear(createDate.getFullYear() + 5);
    return createDate.toISOString();
  }
}
