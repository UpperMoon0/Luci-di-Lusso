import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, tap} from "rxjs/operators";
import {BehaviorSubject, Observable, of} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class VoucherService {
  constructor(private http: HttpClient) {
  }

  private get httpOptions() {
    return {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Authorization": `Bearer ${localStorage.getItem('accessToken')}`,
      }),
      withCredentials: true,
    };
  }

  getMyVouchers(): Observable<any> {
    return this.http.get<any[]>(`${environment.beApiUrl}/voucher/get-my-vouchers`, this.httpOptions);
  }

  getUsableVouchers(): Observable<any> {
    return this.http.get<any[]>(`${environment.beApiUrl}/voucher/get-usable-vouchers`, this.httpOptions);
  }

  getCustomerPoints(): Observable<any> {
    return this.http.get<any>(`${environment.beApiUrl}/user/get-customer-points`, this.httpOptions);
  }

  redeemVoucher(voucherId: number): Observable<any> {
    return this.http.post<any[]>(`${environment.beApiUrl}/voucher/redeem-voucher`, voucherId, this.httpOptions);
  }
}
