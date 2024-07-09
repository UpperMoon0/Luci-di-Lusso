import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";


const BASIC_URL = 'http://localhost:8080/'; // Define the base URL for the API
@Injectable({
  providedIn: 'root'
})
export class ManagerService {

  constructor(private http: HttpClient) { }

  addDiamond(diamondRequest: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/manager/diamond', diamondRequest,{
      headers: this.createAuthorizationHeader(),
    });
  }

  getDiamondCut(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/manager',{
      headers: this.createAuthorizationHeader(),
    });
  }

  getDiamondColor(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/manager',{
      headers: this.createAuthorizationHeader(),
    });
  }

  getDiamondClarity(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/manager',{
      headers: this.createAuthorizationHeader(),
    });
  }

  getDiamondShape(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/manager',{
      headers: this.createAuthorizationHeader(),
    });
  }

  getAllDiamonds(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/manager/diamonds',{
      headers: this.createAuthorizationHeader(),
    });
  }

  deleteDiamond(diamondId: any): Observable<any> {
    return this.http.delete(BASIC_URL + `api/manager/diamond/${diamondId}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  updateDiamond(diamondId: any, diamondData: any): Observable<any> {
    return this.http.put(BASIC_URL + `api/manager/diamond/${diamondId}`, diamondData, {
      headers: this.createAuthorizationHeader(),
    });
  }



  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('accessToken'));
  }
}
