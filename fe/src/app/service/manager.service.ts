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
    };
  }

  getAllDiamonds(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/product/get-all-diamonds`, this.httpOptions);
  }

  getDiamond(diamondId: any): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/product/get-diamond?id=` + diamondId, this.httpOptions);
  }

  addDiamond(diamond: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/product/add-diamond`, diamond, this.httpOptions);
  }

  saveDiamond(diamond: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/product/save-diamond`, diamond, this.httpOptions);
  }

  deleteDiamond(diamondId: any): Observable<any> {
    return this.http.delete(`${environment.beApiUrl}/product/delete-diamond?id=` + diamondId, this.httpOptions);
  }

  getAllDeliveries(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/delivery/get-unassigned-deliveries`, this.httpOptions);
  }

  getAllDeliverers(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/user/get-deliverers`, this.httpOptions);
  }

  assignDelivery(request: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/delivery/assign-delivery`, request, this.httpOptions);
  }

}
