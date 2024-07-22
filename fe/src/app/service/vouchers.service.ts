import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, tap} from "rxjs/operators";
import {BehaviorSubject, Observable, of} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class VouchersService {
  private profileDataSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

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

  getAllCustomerVouchers(): Observable<any> {
    return this.http.get<any[]>(`${environment.beApiUrl}/voucher/get-all-customer-vouchers`, this.httpOptions);
  }

  getAllVouchers(): Observable<any> {
    return this.http.get<any[]>(`${environment.beApiUrl}/voucher/get-all-vouchers`, this.httpOptions);
  }

  getCustomerPoints(): Observable<any> {
    return this.http.get<any>(`${environment.beApiUrl}/user/customer-points`, this.httpOptions);
  }

  buyVoucher(code: String): Observable<any> {
    return this.http.post<any[]>(`${environment.beApiUrl}/voucher/add-voucher-to-account`, this.httpOptions);
  }
}
