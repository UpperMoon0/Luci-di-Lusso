import { Component, OnInit } from '@angular/core';
import { ToastrService } from "ngx-toastr";
import { JewelryService } from "../service/jewelry.service";
import { ActivatedRoute } from "@angular/router";
import {ColorService} from "../service/color.service";

@Component({
  selector: 'app-collection-page',
  templateUrl: './collection-page.component.html',
  styleUrls: ['./collection-page.component.css']
})
export class CollectionPageComponent implements OnInit {
  protected collection: any = {};
  private jewelries: any[] = [];
  protected jewelriesInCollection: any[] = [];

  constructor(private productService: JewelryService,
              private toastrService: ToastrService,
              private route: ActivatedRoute,
              protected colorService: ColorService) {}

  ngOnInit(): void {
    this.getAllJewelries(() => {
      this.getCollection(+this.route.snapshot.paramMap.get('id'));
    });
  }

  getAllJewelries(callback: () => void): void {
    let request = {types: [], minPrice: 0, maxPrice: 0, keyword: ''};
    this.productService.getJewelryList(request).subscribe({
      next: (response: any) => {
        this.jewelries = response.jewelries;
        callback();
      },
      error: () => {
        this.toastrService.error("Error fetching jewelries");
        callback();
      }
    });
  }

  getCollection(id: number): void {
    this.productService.getCollection(id).subscribe({
      next: (collection: any) => {
        this.collection = collection;
        this.jewelriesInCollection = this.jewelries.filter(jewelryWrapper =>
          collection.jewelries.some(colJewelry => colJewelry.id === jewelryWrapper.jewelry.id)
        );
      },
      error: () => this.toastrService.error("Error fetching collection"),
    });
    console.log(this.jewelriesInCollection)
  }
}
