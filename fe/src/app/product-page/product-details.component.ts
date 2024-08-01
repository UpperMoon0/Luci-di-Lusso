import {Component, OnInit} from '@angular/core';
import {JewelryService} from "../service/jewelry.service";
import {AccountService} from "../service/account.service";
import {ActivatedRoute} from "@angular/router";
import {CartService} from "../service/cart.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  protected jewelry: { id: number,
                       name: string,
                       type: any,
                       description: string,
                       settingPrice: number,
                       laborCost: number,
                       imageUrl: string,
                       diamond: any};
  protected sizes: any[] = [];
  protected isLoggedIn: boolean;
  protected selectedSize: any;
  protected selectedQuantity: number = 1;
  protected activeTab: string = 'description';

  constructor(
    private productService: JewelryService,
    private cartService: CartService,
    private accountService: AccountService,
    private route: ActivatedRoute,
    private toastrService: ToastrService
  ) {
    this.jewelry = {
      id: 0,
      name: '',
      type: {},
      description: '',
      settingPrice: 0,
      laborCost: 0,
      imageUrl: '',
      diamond: {}
    };
  }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.getJewelry(id);
    this.accountService.checkToken();
    this.accountService.isLoggedIn.subscribe(value => {
      this.isLoggedIn = value;
    });
  }

  getJewelry(id: number) {
    this.productService.getJewelry(id).subscribe({
      next: (res) => {
        this.jewelry = res.jewelry;
        this.sizes = res.sizes;
        this.selectedSize = this.sizes[0];
      }
    });
  }

  addToCart() {
    this.cartService.addToCart(this.jewelry.id, this.selectedQuantity, this.selectedSize.id).subscribe({
      next: () => {
        this.toastrService.success('Added to cart');
      },
      error: (res) => {
        this.toastrService.error(res.error.message);
      }
    });
  }

  incrementQuantity(): void {
    this.selectedQuantity++;
  }

  decrementQuantity(): void {
    if (this.selectedQuantity > 1) {
      this.selectedQuantity--;
    }
  }

  changeTab(tabName: string): void {
    this.activeTab = tabName;
  }

  calculatePrice(): number {
    let diamond = this.jewelry.diamond;
    let diamondColorPrice = diamond.color? diamond.color.price : 0;
    let diamondClarityPrice = diamond.clarity? diamond.clarity.price : 0;
    let diamondCutPrice = diamond.cut? diamond.cut.price : 0;
    let diamondShapePriceMultiplier = diamond.shape? diamond.shape.priceMultiplier : 0;
    let selectedSizePriceMultiplier = this.selectedSize? this.selectedSize.priceMultiplier : 0;

    let diamondPrice = (diamondColorPrice + diamondClarityPrice + diamondCutPrice) * diamond.carat * diamondShapePriceMultiplier;
    return this.jewelry.settingPrice * selectedSizePriceMultiplier + this.jewelry.laborCost + diamondPrice;
  }
}
