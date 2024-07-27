import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ManagerService} from "../service/manager.service";
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDeleteComponent} from "./confirm-delete.component";
import {VoucherEditComponent} from "./voucher-edit.component";

@Component({
  selector: 'app-manager-vouchers-list',
  templateUrl: './manager-vouchers-list.component.html',
  styleUrls: ['./manager-vouchers-list.component.css']
})
export class ManagerVouchersListComponent implements OnInit {
  vouchers: any[] = [];

  constructor(private managerService: ManagerService,
              private titleService: Title,
              private toastrService: ToastrService,
              public dialog: MatDialog) {}

  ngOnInit() {
    this.getVouchers();
    this.titleService.setTitle('Voucher List | Luci di Lusso');
  }

  getVouchers(): void {
    this.managerService.getAllVouchers().subscribe(response => {
      this.vouchers = response;
    });
  }

  deleteVoucher(voucherId: any) {
    this.managerService.deleteVoucher(voucherId).subscribe(response => {
      this.toastrService.success('Voucher deleted successfully');
      this.getVouchers();
    });
  }

  openEditVoucherDialog(voucher: any): void {
    this.dialog.open(VoucherEditComponent, {
      data: {
        voucher: voucher,
        refreshList: () => this.getVouchers()
      },
      width: '400px',
    });
  }

  openDeleteConfirmDialog(voucherId: any): void {
    this.dialog.open(ConfirmDeleteComponent, {
      data: {
        entity: 'voucher',
        refreshList: () => this.getVouchers(),
        deleteEntity: () => this.deleteVoucher(voucherId),
        closeDialog: () => this.dialog.closeAll()
      },
      width: '400',
    });
  }

  addVoucher() {
    this.managerService.addVoucher().subscribe(
      () => {
        this.toastrService.success('Voucher added successfully');
        this.getVouchers();
      }
    )
  }
}
