import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { tap } from "rxjs/operators";

interface CartState {
  cartItems: CartItemDTO[];
  totalPrice: number;
  totalItems: number;
}

interface CartItemDTO {
  id: number;
  name: string;
  imageUrl: string;
  size: string;
  price: number;
  quantity: number;
  createAt: string;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartState: BehaviorSubject<CartState> = new BehaviorSubject<CartState>({
    cartItems: [],
    totalPrice: 0,
    totalItems: 0
  });

  constructor(private http: HttpClient) {
    this.getCartItems();
  }

  private get httpOptions() {
    return {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Authorization": `Bearer ${localStorage.getItem('accessToken')}`,
      }),
    };
  }

  addToCart(id: number, quantity: number, sizeId: number) {
    let body = { jewelryId: id, quantity: quantity, sizeId: sizeId };
    return this.http.post<any>(`${environment.beApiUrl}/cart/add`, body, this.httpOptions).pipe(
      tap(() => this.getCartItems())
    );
  }

  updateQuantity(itemId: number, quantity: number) {
    console.log('Updating quantity:', itemId, quantity);
    let body = { itemId: itemId, quantity: quantity };
    return this.http.put<any>(`${environment.beApiUrl}/cart/update-item`, body, this.httpOptions).pipe(
      tap(() => this.getCartItems())
    );
  }

  deleteItem(itemId: number) {
    return this.http.delete<any>(`${environment.beApiUrl}/cart/delete-item?itemId=${itemId}`, this.httpOptions).pipe(
      tap(() => this.getCartItems())
    );
  }

  clearCart() {
    return this.http.delete<any>(`${environment.beApiUrl}/cart/delete-cart`, this.httpOptions).pipe(
      tap(() => this.getCartItems())
    );
  }

  getCartItems() {
    this.http.get<any>(`${environment.beApiUrl}/cart/get-cart`, this.httpOptions).subscribe({
      next: (res: any) => {
        this.cartState.next({
          cartItems: res.cartItems,
          totalPrice: res.totalPrice,
          totalItems: res.totalItems
        });
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  getCartState(): Observable<CartState> {
    return this.cartState.asObservable();
  }
}
