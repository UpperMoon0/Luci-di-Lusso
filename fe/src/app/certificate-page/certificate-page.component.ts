import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AccountService } from '../service/account.service';
import {JewelryService} from "../service/jewelry.service";

@Component({
  selector: 'app-certificate-page',
  templateUrl: './certificate-page.component.html',
  styleUrls: ['./certificate-page.component.css']
})
export class CertificatePageComponent implements OnInit {
  protected diamond: any;

  constructor(private productService: JewelryService,
              private toastrService: ToastrService,
              private accountService: AccountService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getSelectedTypes(+this.route.snapshot.paramMap.get('id'));

    const token = localStorage.getItem('accessToken');
    this.accountService.validateToken(token).subscribe({
      next: (res) => {
        if (res.message == "MANAGER") {
          this.router.navigate(["/manager"]).then(r=> {});
        }
        if (res.message == "DELIVERER") {
          this.router.navigate(["/delivery"]).then(r=> {});
        }
      }
    });
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


