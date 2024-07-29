import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DeliveryService {
  constructor(private http: HttpClient) {}

  private get httpOptions() {
    return {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      }),
      withCredentials: true,
    };
  }

  public getMyDeliveries(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/delivery/get-my-deliveries`, this.httpOptions);
  }

  public completeDelivery(id: number) {
    return this.http.put(`${environment.beApiUrl}/delivery/complete-delivery?id=${id}`, '', this.httpOptions);
  }

  public getAllDeliveries(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/delivery/get-all-deliveries`, this.httpOptions);
  }

  public getAllDeliverers(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/user/get-deliverers`, this.httpOptions);
  }

  public assignDeliverer(request: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/delivery/assign-deliverer`, request, this.httpOptions);
  }

  public unassignDeliverer(deliveryId: number): Observable<any> {
    return this.http.put(`${environment.beApiUrl}/delivery/unassign-deliverer?deliveryId=${deliveryId}`, '', this.httpOptions);
  }
}
