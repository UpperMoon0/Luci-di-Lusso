import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {}

  private get httpOptions() {
    return {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Authorization": `Bearer ${localStorage.getItem('accessToken')}`,
      }),
      withCredentials: true,
    };
  }

  public getOrders(): Observable<any> {
    return this.http.get<any[]>(`${environment.beApiUrl}/order/get-orders`, this.httpOptions);
  }

  public getOrderDetails(id: number): Observable<any> {
    return this.http.get<any>(`${environment.beApiUrl}/order/get-order-details?orderId=${id}`, this.httpOptions);
  }

}
