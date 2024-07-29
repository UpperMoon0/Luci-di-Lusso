import {Component, OnInit} from '@angular/core';
import {JewelryService} from "../service/jewelry.service";

@Component({
  selector: 'app-home-collection-row',
  templateUrl: './home-collection-row.component.html',
  styleUrls: ['./home-collection-row.component.css',]
})
export class HomeCollectionRowComponent implements OnInit {
  collections: any[] = [];

  constructor(private productService: JewelryService) {
  }

  ngOnInit(): void {
    this.productService.getAllCollections().subscribe(res => {
      this.collections = res;
    })
  }
}
