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

  addToCart(id: number) {
    let body = { productId: id, quantity: 1 };
    return this.http.post<any>(`${environment.apiUrl}/cart/add`, body, this.httpOptions).pipe(
      tap(() => this.getCartItems())
    );
  }

  getCartItems() {
    this.http.get(`${environment.apiUrl}/cart/get-cart`, this.httpOptions).subscribe({
      next: (res: any) => {
        this.cartItemList = res.cartItems;
        this.totalPrice.next(res.totalPrice);
      },
      error: (error) => {
        console.error('Error getting cart items:', error);
      }
    });
  }
}
