import { Component, OnInit } from '@angular/core';
import { ToastrService } from "ngx-toastr";
import { ProductService } from "../service/product.service";
import {ColorService} from "../service/color.service";

@Component({
  selector: 'app-collection-list-page',
  templateUrl: './collection-list-page.component.html',
  styleUrls: ['./collection-list-page.component.css']
})
export class CollectionListPageComponent implements OnInit {
  protected collections: any[] = [];

  constructor(private productService: ProductService,
              private toastrService: ToastrService) {}

  ngOnInit(): void {
    this.productService.getAllCollections().subscribe({
      next: (res) => {
        this.collections = res;
      },
      error: () => {
        this.toastrService.error("Failed to get collections");
      }
    });
  }
}
