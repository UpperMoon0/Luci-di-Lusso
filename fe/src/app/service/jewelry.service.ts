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
    this.getAllJewelryTypes().subscribe();
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

  public getJewelryTypesObservable(): Observable<String[]> {
    return this.productTypes.asObservable();
  }

  getJewelry(id: number): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/jewelry/get-jewelry?id=` + id, this.httpOptions);
  }

  public getJewelryList(request: any): Observable<any> {
    if (!request) request = {types: {}, minPrice: 0, maxPrice: 0, keyword: ''};
    return this.http.post(`${environment.beApiUrl}/jewelry/get-jewelry-list`, request, this.httpOptions);
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

  public getAllJewelrySizes(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/jewelry/get-all-jewelry-sizes`, this.httpOptions);
  }

  public saveJewelrySize(request: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/jewelry/save-jewelry-size`, request, this.httpOptions);
  }

  public toggleJewelrySizeStatus(id: number): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/jewelry/toggle-jewelry-size-status?id=${id}`,'', this.httpOptions);
  }

  public getAllJewelryTypes(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/jewelry/get-all-jewelry-types`, this.httpOptions).pipe(
      tap(response => {
        const types = response.map((item: any) => item.type);
        this.productTypes.next(types);
        this.colorService.mapTypesToColors(types);
      })
    );
  }

  public saveJewelryType(request: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/jewelry/save-jewelry-type`, request, this.httpOptions).pipe(
      tap(() => this.getAllJewelryTypes().subscribe())
    );
  }

  public toggleJewelryTypeStatus(id: number): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/jewelry/toggle-jewelry-type-status?id=${id}`,'', this.httpOptions).pipe(
      tap(() => this.getAllJewelryTypes().subscribe())
    );
  }
}
