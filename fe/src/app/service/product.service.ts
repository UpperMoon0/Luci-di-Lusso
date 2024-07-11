import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import { environment } from "../../environments/environment";
import {tap} from "rxjs/operators";
import {ColorService} from "./color.service";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  productTypes: BehaviorSubject<String[]> = new BehaviorSubject<String[]>([]);

  private apiUrl = environment.beApiUrl;

  constructor(private http: HttpClient, private colorService: ColorService) {
    this.getAllTypes().subscribe();
  }

  private get httpOptions() {
    return {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      }),
    };
  }

  getJewelry(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/product/get-jewelry?id=` + id, this.httpOptions);
  }

  public getJewelries(request: any): Observable<any> {
    // Get all jewelries by default
    if (!request.types) {
      return this.http.get(`${this.apiUrl}/product/get-all-jewelries`, this.httpOptions);
    } else {
      return this.http.post<any>(`${this.apiUrl}/product/get-jewelries`, request, this.httpOptions);
    }
  }

  public getAllTypes(): Observable<any> {
    return this.http.get<any[]>(`${this.apiUrl}/product/get-all-jewelry-types`, this.httpOptions).pipe(
      tap(types => {
        this.productTypes.next(types);
        this.colorService.mapTypesToColors(types);
      })
    );
  }

  public getTest(): Observable<any> {
    return this.http.get<Object>(`${this.apiUrl}/api/product/test`, this.httpOptions);
  }

  public getProductTypes(): Observable<String[]> {
    return this.productTypes.asObservable();
  }
}
