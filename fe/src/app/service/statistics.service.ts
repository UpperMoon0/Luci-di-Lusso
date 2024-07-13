import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ColorService} from "./color.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StatisticsService {

  private apiUrl = environment.beApiUrl;

  constructor(private http: HttpClient, private colorService: ColorService) {
  }

  private get httpOptions() {
    return {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      }),
    };
  }

  getSaleStatistics(): Observable<any> {
    return this.http.get<any[]>(`${this.apiUrl}/statistics/get-sale-statistics`, this.httpOptions);
  }

  getJewelriesSaleStatistics(): Observable<any> {
    return this.http.get<any[]>(`${this.apiUrl}/statistics/get-jewelries-sale-statistics`, this.httpOptions);
  }

  getCustomersCreationStatistics(): Observable<any> {
    return this.http.get<any[]>(`${this.apiUrl}/statistics/get-customers-creation-statistics`, this.httpOptions);
  }

}
