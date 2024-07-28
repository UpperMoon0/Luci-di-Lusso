import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ManagerService} from "../../service/manager.service";
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";
import {JewelryEditFormComponent} from "./jewelry-edit-form.component";
import {ConfirmDeleteComponent} from "../confirm-delete.component";
import {JewelryService} from "../../service/jewelry.service";

@Component({
  selector: 'app-manager-jewelry-list',
  templateUrl: './manager-jewelry-list.component.html',
  styleUrls: ['./manager-jewelry-list.component.css']
})
export class ManagerJewelryListComponent implements OnInit {
  jewelries: any[] = [];

  constructor(private jewelryService: JewelryService,
              private titleService: Title,
              private toastrService: ToastrService,
              public dialog: MatDialog) {}

  ngOnInit() {
    this.getJewelries();
    this.titleService.setTitle('Diamond List | Luci di Lusso');
  }

  getJewelries(): void {
    this.jewelryService.getAllJeweleries().subscribe(response => {
      this.jewelries = response;
    });
    this.dialog.closeAll();
  }

  toggleJewelryStatus(jewelryId: any) {
    this.jewelryService.toggleJewelryStatus(jewelryId).subscribe({
      next: () => {
        this.toastrService.success('Jewelry status updated successfully');
        this.getJewelries();
      },
      error: () => this.toastrService.error('Failed to update jewelry status')
    });
  }

  openJewelryDialog(jewelry: any): void {
    this.dialog.open(JewelryEditFormComponent, {
      data: {
        jewelry: jewelry,
        refreshList: () => this.getJewelries()
      },
      width: '400px',
    });
  }
}
