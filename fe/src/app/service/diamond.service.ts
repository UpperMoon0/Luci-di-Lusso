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

  public deleteDiamondColor(id: number): Observable<any> {
    return this.http.delete(`${environment.beApiUrl}/diamond/delete-diamond-color?id=${id}`, this.httpOptions);
  }

  public deleteDiamondClarity(id: number): Observable<any> {
    return this.http.delete(`${environment.beApiUrl}/diamond/delete-diamond-clarity?id=${id}`, this.httpOptions);
  }

  public deleteDiamondCut(id: number): Observable<any> {
    return this.http.delete(`${environment.beApiUrl}/diamond/delete-diamond-cut?id=${id}`, this.httpOptions);
  }

  public deleteDiamondShape(id: number): Observable<any> {
    return this.http.delete(`${environment.beApiUrl}/diamond/delete-diamond-shape?id=${id}`, this.httpOptions);
  }
}
