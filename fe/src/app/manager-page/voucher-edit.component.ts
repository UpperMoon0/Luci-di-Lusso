import {Component, Inject, OnInit} from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {ManagerService} from "../service/manager.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-voucher-edit',
  templateUrl: './voucher-edit.component.html',
  styleUrls: ['./voucher-edit.component.css']
})
export class VoucherEditComponent implements OnInit {

  voucherEditForm = new FormGroup({
    code: new FormControl(this.data.voucher.code),
    discount: new FormControl(this.data.voucher.description),
    expireAt: new FormControl(this.data.voucher.expireAt),
    price: new FormControl(this.data.voucher.price),
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private managerService: ManagerService,
    private toastrService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.voucherEditForm.patchValue({
      code: this.data.voucher.code,
      discount: this.data.voucher.discount,
      expireAt: this.data.voucher.expireAt,
      price: this.data.voucher.price,
    });
  }

  onSubmit(): void {
    let voucher = {
      id: this.data.voucher.id,
      code: this.voucherEditForm.value.code,
      discount: this.voucherEditForm.value.discount,
      expireAt: this.voucherEditForm.value.expireAt,
      price: this.voucherEditForm.value.price,
    };

    this.managerService.updateVoucher(voucher).subscribe(
      () => {
        this.toastrService.success('Voucher updated successfully');
        this.data.refreshList();
      },
      () => this.toastrService.error('Voucher update failed')
    )
  }
}
