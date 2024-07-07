import { Component, OnInit } from '@angular/core';
import { ProductService } from "../service/product.service";
import { AccountService } from "../service/account.service";
import { ActivatedRoute } from "@angular/router";
import { CartService } from "../service/cart.service";

interface ProductDetailsDTO {
  id: number;
  name: string;
  price: number;
  description: string;
  imageUrl: string;
  type: string;
  diamondCarat: number
  diamondShape: string;
  diamondCut: string;
  diamondColor: string;
  diamondClarity: string;
  sizes: Array<{ id: number, size: number, unit: string }>;
}

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  product: ProductDetailsDTO = {} as ProductDetailsDTO;
  isLoggedIn: boolean;
  selectedSize: string;
  selectedSizeId: number;
  selectedQuantity: number = 1;
  activeTab: string = 'description';

  constructor(
    private productService: ProductService,
    private cartService: CartService,
    private accountService: AccountService,
    private route: ActivatedRoute
  ) {}

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
        this.product = res;
        if (this.product.sizes && this.product.sizes.length > 0) {
          const firstSize = this.product.sizes[0];
          this.selectedSize = `${firstSize.size} ${firstSize.unit}`;
          this.selectedSizeId = firstSize.id;
        }
      },
      error: (err) => {
        console.log("Error: ", err);
      }
    });
  }

  addToCart() {
    this.cartService.addToCart(this.product.id, this.selectedQuantity, this.selectedSizeId).subscribe({
      next: (response) => {
        console.log('Product added to cart:', response);
      },
      error: (error) => {
        console.error('Error adding product to cart:', error);
      }
    });
  }

  selectSize(size: { id: number; size: number; unit: string }): void {
    this.selectedSize = `${size.size} ${size.unit}`;
    this.selectedSizeId = size.id;
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
}
