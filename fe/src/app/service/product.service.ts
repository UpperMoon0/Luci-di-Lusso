import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = environment.apiUrl;
  httpOptions: any;
  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      }),
    };
  }

  getJewelry(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/product/get-jewelry?id=` + id);
  }

  public getJewelries(request: any): Observable<any>{
    // Get all jewelries by default
    if (!request.types) {
      return this.http.get(`${this.apiUrl}/product/get-all-jewelries`);
    } else {
      return this.http.post<any>(`${this.apiUrl}/product/get-jewelries`, request);
    }
  }

  public getAllTypes(): Observable<any> {
    return this.http.get<any[]>(`${this.apiUrl}/product/get-all-jewelry-types`, this.httpOptions);
  }

  public addProduct(Product : any): Observable<any>{
    return this.http.post<any>(`${this.apiUrl}/api/product/addProduct`,Product,this.httpOptions);
  }
  public updateProduct(Product : any): Observable<any>{
    return this.http.put<any>(`${this.apiUrl}/api/product/update`,Product,this.httpOptions);
  }
  public deleteProduct(ProductId : number): Observable<any>{
    return this.http.delete<void>(`${this.apiUrl}/api/product/delete/${ProductId}`,this.httpOptions);
  }
  public getTest(): Observable<any>{
    return this.http.get<Object>(`${this.apiUrl}/api/product/test`, this.httpOptions);
  }
}
