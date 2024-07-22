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

  getAllDiamonds(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/product/get-all-diamonds`, this.httpOptions);
  }

  getDiamond(diamondId: any): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/product/get-diamond?id=` + diamondId, this.httpOptions);
  }

  addDiamond(): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/product/add-diamond`, '',this.httpOptions);
  }

  updateDiamond(diamond: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/product/update-diamond`, diamond, this.httpOptions);
  }

  deleteDiamond(diamondId: number): Observable<any> {
    return this.http.delete(`${environment.beApiUrl}/product/delete-diamond?id=` + diamondId, this.httpOptions);
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

  getAllJeweleries(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/product/get-all-jewelries`, this.httpOptions);
  }

  addJewelry(): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/product/add-jewelry`, '',this.httpOptions);
  }

  deleteJewelry(id: number): Observable<any> {
    return this.http.delete(`${environment.beApiUrl}/product/delete-jewelry?id=${id}`, this.httpOptions);
  }

  updateJewelry(request: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/product/update-jewelry`, request, this.httpOptions);
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
