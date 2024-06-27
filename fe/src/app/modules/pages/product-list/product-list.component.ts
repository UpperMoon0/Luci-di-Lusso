import {Component, Directive} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, NgModel} from "@angular/forms";
import {ProductService} from "../../service/product.service";
import {Router} from "@angular/router";
import {AccountService} from "../../auth/services/account.service";
import {AuthGoogleService} from "../../../core/shared/auth-google.service";
import {ToastrService} from "ngx-toastr";
import {CartService} from "../../service/cart.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent {

  selectedRangePrice: String = "";
  productsList: any[];
  selectedTags: any[] = [];
  public tagsList: any[] = [];
  isLoginUser: boolean = false;

  constructor(private productService: ProductService,
              private router: Router,
              private accountService: AccountService,
              private authGoogleService: AuthGoogleService,
              private toastrService: ToastrService,
              private cartService: CartService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.isLoginUser = localStorage.getItem("user") != null;
    // this.checkboxGroup = this.formBuilder.group({value: []});
    this.getAllTags();
  }

  private getAllTags(): void {
    this.productService.getAllTags().subscribe((res : String[])=> {
      this.tagsList = res;
    }, error => {
      this.toastrService.error(error.message);
    })
  }

  //Get Products after selecting tags
  public getSelectedTags(tag : string) {
    if (this.selectedTags.includes(tag)) {
      for (let i = 0; i < this.selectedTags.length; i++) {
        if (this.selectedTags[i] == tag) {
          this.selectedTags.splice(i, 1);
        }
      }
      const id = document.getElementById(tag);
      id.style.fontWeight = "normal";
      id.style.backgroundColor = "white";
      id.style.color = "black";
    } else {
      this.selectedTags.push(tag);
      const id = document.getElementById(tag);
      id.style.width = "100%";
      id.style.fontWeight = "bold";
      id.style.backgroundColor = "#173334FF";
      id.style.color = "white";
    }
    this.getProducts();
  }

  getProducts() {
    for (let i = 0; i < this.selectedTags.length; i++) {
      if (this.selectedTags[i].includes(" ")) {
        this.selectedTags[i] = this.selectedTags[i].split(" ")[0] + "_" + this.selectedTags[i].split(" ")[1];
      }
      this.selectedTags[i].toUpperCase();
    }
    let tags: String[] = [];
    let minPrice: number = 0;
    let maxPrice: number = 0;
    if (!(this.selectedTags.length != 0)) {
      tags = this.selectedTags;
    }
    switch (this.selectedRangePrice) {
      case "$ 0 - 100":
        minPrice = 0;
        maxPrice = 100;
        break;
      case "$ 100 - 500":
        minPrice = 100;
        maxPrice = 500;
        break;
      case "$ 500 - 1000":
        minPrice = 500;
        maxPrice = 1000;
        break;
      case "$ 1000 - 4000":
        minPrice = 1000;
        maxPrice = 4000;
        break;
      case "More than $ 4000":
        minPrice = 4000;
        maxPrice = 0;
        break;
      default:
        minPrice = 0;
        maxPrice = 0;
    }
    const request = {
      tags : tags,
      minPrice : minPrice,
      maxPrice : maxPrice
    } as any;
    this.productService.getAllJewelries(request).subscribe((res) => {
      this.productsList = res.data;
    }, error => {
      this.toastrService.error("Error in getting products list");
    })
  }


}
