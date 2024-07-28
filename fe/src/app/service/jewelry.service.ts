import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { BehaviorSubject, Observable} from "rxjs";
import { environment } from "../../environments/environment";
import { tap } from "rxjs/operators";
import { ColorService } from "./color.service";

@Injectable({
  providedIn: 'root'
})
export class JewelryService {
  productTypes: BehaviorSubject<String[]> = new BehaviorSubject<String[]>([]);

  constructor(private http: HttpClient, private colorService: ColorService) {
    this.getAllTypes().subscribe();
  }

  private get httpOptions() {
    return {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      }),
      withCredentials: true,
    };
  }

  getJewelry(id: number): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/jewelry/get-jewelry?id=` + id, this.httpOptions);
  }

  public getJewelries(request: any): Observable<any> {
    if (!request) request = {types: {}, minPrice: 0, maxPrice: 0, keyword: ''};
    return this.http.post(`${environment.beApiUrl}/jewelry/get-jewelry-list`, request, this.httpOptions);
  }

  public getAllTypes(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/jewelry/get-all-jewelry-types`, this.httpOptions).pipe(
      tap(response => {
        const types = response.types.map((item: any) => item.type);
        this.productTypes.next(types);
        this.colorService.mapTypesToColors(types);
      })
    );
  }

  public getDiamond(id: number): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/jewelry/get-diamond?id=` + id, this.httpOptions);
  }

  public getProductTypes(): Observable<String[]> {
    return this.productTypes.asObservable();
  }

  public getAllCollections(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/jewelry/get-all-collections`);
  }

  public getCollection(id: number): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/jewelry/get-collection?id=` + id);
  }

  getAllJeweleries(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/jewelry/get-all-jewelries`, this.httpOptions);
  }

  toggleJewelryStatus(id: number): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/jewelry/toggle-jewelry-status?id=${id}`,'', this.httpOptions);
  }

  saveJewelry(request: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/jewelry/save-jewelry`, request, this.httpOptions);
  }
}
