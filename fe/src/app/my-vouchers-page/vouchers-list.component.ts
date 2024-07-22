import {Component, OnInit} from '@angular/core';
import {VoucherService} from "../service/voucher.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-vouchers-list',
  templateUrl: './vouchers-list.component.html',
  styleUrls: ['./vouchers-list.component.css']
})
export class VouchersListComponent implements OnInit {
  vouchers: any[] = [];
  myVouchers: any[] = [];
  customerPoints: number;

  constructor(public vouchersService: VoucherService,
              private toastrService: ToastrService)
  {}

  ngOnInit(): void {
    this.getMyVouchers();
    this.getCustomerPoints();
  }

  getAllVouchers() {
    this.vouchersService.getUsableVouchers().subscribe({
      next: (res) => {
        this.vouchers = res
        this.vouchers = this.vouchers.filter(voucher => this.myVouchers.findIndex(myVoucher => myVoucher.id === voucher.id) === -1);
      }
    })
  }

  getMyVouchers() {
    this.vouchersService.getMyVouchers().subscribe({
      next: (res) => {
        this.myVouchers = res
        this.getAllVouchers();
      }
    })
  }

  getCustomerPoints() {
    this.vouchersService.getCustomerPoints().subscribe({
      next: (res) => { this.customerPoints = res }
    })
  }

  redeemVoucher(voucherId: number) {
    this.vouchersService.redeemVoucher(voucherId).subscribe({
      next: (res) => {
        this.toastrService.success(res.message)
        this.getMyVouchers();
      }
    })
  }
}
