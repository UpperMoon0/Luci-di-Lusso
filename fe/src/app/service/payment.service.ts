import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { StripeService } from 'ngx-stripe';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient) { }

  createCharge(token: string, amount: number, customerName: string): Observable<any> {
    let amountInCent: number = amount * 100;
    return this.http.post(`${environment.apiUrl}/payment/create-charge`,
      { token: token, amount: amountInCent, customerName: customerName});
  }
}
