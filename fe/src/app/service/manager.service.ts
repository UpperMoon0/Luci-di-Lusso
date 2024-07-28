import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ManagerService {

  constructor(private http: HttpClient) { }

  private get httpOptions() {
    return {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Authorization": `Bearer ${localStorage.getItem('accessToken')}`,
      }),
      withCredentials: true,
    };
  }

  getAllDeliveries(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/delivery/get-unassigned-deliveries`, this.httpOptions);
  }

  getAllDeliverers(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/user/get-deliverers`, this.httpOptions);
  }

  assignDeliverer(request: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/delivery/assign-deliverer`, request, this.httpOptions);
  }

  getAllVouchers(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/voucher/get-all-vouchers`, this.httpOptions);
  }

  addVoucher(): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/voucher/create-voucher`, '',this.httpOptions);
  }

  deleteVoucher(id: number): Observable<any> {
    return this.http.delete(`${environment.beApiUrl}/voucher/delete-voucher?id=${id}`, this.httpOptions);
  }

  updateVoucher(request: any): Observable<any> {
    return this.http.put(`${environment.beApiUrl}/voucher/update-voucher`, request, this.httpOptions);
  }
}
