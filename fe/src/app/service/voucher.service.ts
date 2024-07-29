import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
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

  getAllVouchers(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/voucher/get-all-vouchers`, this.httpOptions);
  }

  toggleVoucherStatus(id: number): Observable<any> {
    return this.http.delete(`${environment.beApiUrl}/voucher/toggle-voucher-status?id=${id}`, this.httpOptions);
  }

  saveVoucher(request: any): Observable<any> {
    return this.http.put(`${environment.beApiUrl}/voucher/save-voucher`, request, this.httpOptions);
  }
}
