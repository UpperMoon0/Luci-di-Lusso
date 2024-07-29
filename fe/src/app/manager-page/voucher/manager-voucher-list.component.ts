import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";
import {VoucherService} from "../../service/voucher.service";
import {EditFormComponent} from "../edit-form.component";

@Component({
  selector: 'app-manager-voucher-list',
  templateUrl: './manager-voucher-list.component.html',
  styleUrls: ['./manager-voucher-list.component.css'],
  encapsulation: ViewEncapsulation.ShadowDom
})
export class ManagerVoucherListComponent implements OnInit {
  vouchers: any[] = [];

  constructor(private voucherService: VoucherService,
              private titleService: Title,
              private toastrService: ToastrService,
              public dialog: MatDialog) {}

  ngOnInit() {
    this.getVouchers();
    this.titleService.setTitle('Voucher list');
  }

  getVouchers(): void {
    this.voucherService.getAllVouchers().subscribe(response => {
      this.vouchers = response;
    });
    this.dialog.closeAll();
  }

  toggleVoucherStatus(voucherId: any) {
    this.voucherService.toggleVoucherStatus(voucherId).subscribe({
      next: () => {
        this.toastrService.success('Voucher status updated successfully');
        this.getVouchers();
      },
      error: () => this.toastrService.error('Failed to update voucher status')
    });
  }

  openVoucherDialog(voucher: any): void {
    let fields:any[] = [
      {name: 'id', label: 'none', type: 'number', value: 0},
      {name: 'code', label: 'Code', type: 'string', value: ''},
      {name: 'discount', label: 'Discount', type: 'number', value: '10'},
      {name: 'price', label: 'Price', type: 'number', value: '1000'},
      {name: 'expireAt', label: 'Expire at', type: 'datetime', value: new Date(new Date().setDate(new Date().getDate() + 30))},
    ];

    if (voucher) {
      fields = [
        {name: 'id', label: 'none', type: 'number', value: voucher.id},
        {name: 'code', label: 'Code', type: 'string', value: voucher.code},
        {name: 'discount', label: 'Discount', type: 'number', value: voucher.discount},
        {name: 'price', label: 'Price', type: 'number', value: voucher.price},
        {name: 'expireAt', label: 'Expire at', type: 'datetime', value: new Date(voucher.expireAt)},
      ];
    }

    this.dialog.open(EditFormComponent, {
      data: {
        fields: fields,
        save: (voucher: any) => this.saveVoucher(voucher)
      },
      width: '400px',
    });
  }

  saveVoucher(voucher: any): void {
    this.voucherService.saveVoucher(voucher).subscribe({
      next: () => {
        this.toastrService.success('Voucher saved successfully');
        this.getVouchers();
      },
      error: () => this.toastrService.error('Failed to save voucher')
    });
  }
}
