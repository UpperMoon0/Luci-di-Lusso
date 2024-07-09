import { Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { ProductDTO } from "../_models/productDTO";
import { HelperService } from "../service/helper.service";
import { ProductService} from "../../service/product-service";
import { StorageService } from "../../service/storage.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-product-page',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent {
  public Products: any[] = [];
  public editProduct!: any;
  public deleteProduct!: any;

  constructor(private helperService: HelperService,private ProductService: ProductService,private storageService: StorageService,
              private router: Router) { }

  ngOnInit(): void {
    this.getProducts()
    throw new Error('Method not implemented.');
  }
  public getProducts(): void{

  }
  public onAddProduct(addForm: NgForm): void {

  }
  public logoutPage():void{
    this.storageService.clean();
    this.router.navigate(['/login']);

  }

  public onUpdateProduct(Product: ProductDTO): void {

  }

  public onDeleteEmloyee(ProductId: any): void {

  }

  public searchProducts(key: string): void {
    console.log(key);
    const results: ProductDTO[] = [];
    for (const Product of this.Products) {
      if (Product.name?.toLowerCase().indexOf(key.toLowerCase()) !== -1
        // || Product.mail?.toLowerCase().indexOf(key.toLowerCase()) !== -1
        // || Product.phone?.toLowerCase().indexOf(key.toLowerCase()) !== -1
        // || Product.jobTitle?.toLowerCase().indexOf(key.toLowerCase()) !== -1
      ) {
        results.push(Product);
      }
    }
    this.Products = results;
    if (results.length === 0 || !key) {
      this.getProducts();
    }
  }

  public onOpenModal(Product: any, mode: string): void{
    const container = document.getElementById('main-container')!;
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addProductModal');
    }
    if (mode === 'edit') {
      this.editProduct = Product;
      button.setAttribute('data-target', '#updateProductModal');
    }
    if (mode === 'delete') {
      this.deleteProduct = Product;
      button.setAttribute('data-target', '#deleteProductModal');
    }
    container.appendChild(button);
    button.click();
  }

}
