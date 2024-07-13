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

  public getDeliveries(): Observable<any> {
    return this.http.get<any[]>(`${environment.beApiUrl}/delivery/get-deliveries`, this.httpOptions);
  }

  public completeDelivery(id: number) {
    return this.http.put<any>(`${environment.beApiUrl}/delivery/complete-delivery?id=${id}`, this.httpOptions);
  }
}
