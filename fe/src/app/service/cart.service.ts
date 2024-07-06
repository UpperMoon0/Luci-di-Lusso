import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { tap } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  public cartItemList: any[] = [];
  public totalPrice: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  public totalItems: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  private httpOptions: any;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Authorization": `Bearer ${localStorage.getItem('accessToken')}`,
      }),
    };
    this.getCartItems();
  }

  addToCart(id: number, quantity: number, sizeId: number) {
    let body = { jewelryId: id, quantity: quantity, sizeId: sizeId };
    return this.http.post<any>(`${environment.apiUrl}/cart/add`, body, this.httpOptions).pipe(
      tap(() => this.getCartItems())
    );
  }

  updateQuantity(itemId: number, quantity: number) {
    console.log('Updating quantity:', itemId, quantity);
    let body = { itemId: itemId, quantity: quantity };
    return this.http.put<any>(`${environment.apiUrl}/cart/update-item`, body, this.httpOptions).pipe(
      tap(() => this.getCartItems())
    );
  }

  deleteItem(itemId: number) {
    return this.http.delete<any>(`${environment.apiUrl}/cart/delete-item?itemId=${itemId}`, this.httpOptions).pipe(
      tap(() => this.getCartItems())
    );
  }

  clearCart() {
    return this.http.delete<any>(`${environment.apiUrl}/cart/delete-cart`, this.httpOptions).pipe(
      tap(() => this.getCartItems())
    );
  }

  getCartItems() {
    this.http.get<any>(`${environment.apiUrl}/cart/get-cart`, this.httpOptions).subscribe({
      next: (res: any) => {
        this.cartItemList = res.cartItems;
        this.totalPrice.next(res.totalPrice);
        this.totalItems.next(res.totalItems);
      },
      error: (error) => {
        console.error('Error getting cart items:', error);
      }
    });
  }
}
