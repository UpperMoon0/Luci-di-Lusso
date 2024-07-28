import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { BehaviorSubject, Observable} from "rxjs";
import { environment } from "../../environments/environment";
import { ColorService } from "./color.service";

@Injectable({
  providedIn: 'root'
})
export class DiamondService {

  constructor(private http: HttpClient, private colorService: ColorService) {
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

  public getAllDiamondProperties(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/diamond/get-all-diamond-properties`, this.httpOptions);
  }

  getAllDiamonds(): Observable<any> {
    return this.http.get(`${environment.beApiUrl}/diamond/get-all-diamonds`, this.httpOptions);
  }

  public saveDiamondColor(request: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/diamond/save-diamond-color`, request, this.httpOptions);
  }

  public saveDiamondClarity(request: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/diamond/save-diamond-clarity`, request, this.httpOptions);
  }

  public saveDiamondCut(request: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/diamond/save-diamond-cut`, request, this.httpOptions);
  }

  public saveDiamondShape(request: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/diamond/save-diamond-shape`, request, this.httpOptions);
  }

  saveDiamond(diamond: any): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/diamond/save-diamond`, diamond, this.httpOptions);
  }

  public toggleDiamondColorStatus(id: number): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/diamond/toggle-diamond-color-status?id=${id}`, '', this.httpOptions);
  }

  public toggleDiamondClarityStatus(id: number): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/diamond/toggle-diamond-clarity-status?id=${id}`, '', this.httpOptions);
  }

  public toggleDiamondCutStatus(id: number): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/diamond/toggle-diamond-cut-status?id=${id}`, '', this.httpOptions);
  }

  public toggleDiamondShapeStatus(id: number): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/diamond/toggle-diamond-shape-status?id=${id}`, '', this.httpOptions);
  }

  toggleDiamondStatus(diamondId: number): Observable<any> {
    return this.http.post(`${environment.beApiUrl}/diamond/toggle-diamond-status?id=` + diamondId, '', this.httpOptions);
  }
}
