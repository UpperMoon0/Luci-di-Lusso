import {Component, Input, OnInit} from '@angular/core';
import {NumberService} from "../../../service/number.service";
import {ProductService} from "../../../../service/product-service";
import {ToastrService} from "ngx-toastr";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-ecommerce',
  templateUrl: './ecommerce.component.html',
  styleUrls: ['./ecommerce.component.css']
})
export class EcommerceComponent implements OnInit {
  @Input() productId = 1;
  product:any;
  totalProduct =1;

  constructor(private numberFormat: NumberService,
              private productService: ProductService,
              private toastrService: ToastrService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {

  }
  convertNumber(number){
    return this.numberFormat.convertNumber(number);
  }
  changeTotalProduct(number:number) {
    if(this.totalProduct == 1 && number <0){
      this.toastrService.error("số lượng không thể nhỏ hơn 1");

    } else {
      this.totalProduct +=number
    }
  }

}
