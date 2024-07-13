import {Component, OnInit} from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import {ProductService} from "../service/product.service";

@Component({
  selector: 'app-certificate-page',
  templateUrl: './certificate-page.component.html',
  styleUrls: ['./certificate-page.component.css']
})
export class CertificatePageComponent implements OnInit {
  protected diamond;
  // @ts-ignore
  constructor(private productService: ProductService,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.getSelectedTypes(1);
  }

  public getSelectedTypes(id: number): void {
    this.productService.getDiamond(id).subscribe({
      next: (diamond: any) => {
        this.diamond = diamond;
      },
      error: () => this.toastrService.error("Error fetching diamond"),
    });
  }
}
