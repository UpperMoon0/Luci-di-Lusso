import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ManagerService} from "../../service/manager.service";
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";
import {JewelryEditFormComponent} from "./jewelry-edit-form.component";
import {ConfirmDeleteComponent} from "../confirm-delete.component";

@Component({
  selector: 'app-manager-jewelry-list',
  templateUrl: './manager-jewelry-list.component.html',
  styleUrls: ['./manager-jewelry-list.component.css']
})
export class ManagerJewelryListComponent implements OnInit {
  jewelries: any[] = [];

  constructor(private managerService: ManagerService,
              private titleService: Title,
              private toastrService: ToastrService,
              public dialog: MatDialog) {}

  ngOnInit() {
    this.getJewelries();
    this.titleService.setTitle('Diamond List | Luci di Lusso');
  }

  getJewelries(): void {
    this.managerService.getAllJeweleries().subscribe(response => {
      this.jewelries = response;
    });
  }

  deleteJewelry(jewelryId: any) {
    this.managerService.deleteJewelry(jewelryId).subscribe(response => {
      this.toastrService.success('Jewelry deleted successfully');
      this.getJewelries();
    });
  }

  openEditJewelryDialog(jewelry: any): void {
    this.dialog.open(JewelryEditFormComponent, {
      data: {
        jewelry: jewelry,
        refreshList: () => this.getJewelries()
      },
      width: '400px',
    });
  }

  openDeleteConfirmDialog(jewelryId: any): void {
    this.dialog.open(ConfirmDeleteComponent, {
      data: {
        entity: 'jewelry',
        refreshList: () => this.getJewelries(),
        deleteEntity: () => this.deleteJewelry(jewelryId),
        closeDialog: () => this.dialog.closeAll()
      },
      width: '400',
    });
  }

  addJewelry() {
    this.managerService.addJewelry().subscribe(
      () => {
        this.toastrService.success('Jewelry added successfully');
        this.getJewelries();
      }
    )
  }
}
