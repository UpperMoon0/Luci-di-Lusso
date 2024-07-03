import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  constructor(private http: HttpClient, private router: Router) { }

  getJewelry(id: number): Observable<any> {
    // Pass headers as an option to the http.get method
    return this.http.get('http://localhost:8081/product/get-jewelry?id=' + id);
  }
}
