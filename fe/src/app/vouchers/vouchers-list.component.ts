import {Component, OnInit} from '@angular/core';
import {VouchersService} from "../service/vouchers.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-vouchers-list',
  templateUrl: './vouchers-list.component.html',
  styleUrls: ['./vouchers-list.component.css']
})
export class VouchersListComponent implements OnInit {

  vouchers: any[] = [];
  customerPoints: number;

  constructor(public vouchersService: VouchersService,
              private toastrService: ToastrService)
  {}

  ngOnInit(): void {
    this.getAllVouchers();
    this.getCustomerPoints();
  }

  getAllVouchers() {
    this.vouchersService.getAllVouchers().subscribe({
      next: (res) => { this.vouchers = res.vouchers }
    })
  }

  getCustomerPoints() {
    this.vouchersService.getCustomerPoints().subscribe({
      next: (res) => { this.customerPoints = res.points }
    })
  }

  buyVoucher(voucherCode: String) {
    this.vouchersService.buyVoucher(voucherCode).subscribe({
      next: (res) => { this.toastrService.success(res.message)}
    })
  }

}
