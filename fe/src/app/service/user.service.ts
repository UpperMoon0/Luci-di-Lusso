import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { Observable, BehaviorSubject, of, throwError } from "rxjs";
import { tap, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private profileDataSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  constructor(private http: HttpClient) {
    this.init();
  }

  private get httpOptions() {
    return {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Authorization": `Bearer ${localStorage.getItem('accessToken')}`,
      }),
    };
  }

  private init() {
    this.getProfile().pipe(
      catchError(error => {
        console.error(error);
        return of(null);
      })
    ).subscribe(data => {
      this.profileDataSubject.next(data);
    });
  }

  getProfile(): Observable<any> {
    if (this.profileDataSubject.value) {
      return of(this.profileDataSubject.value);
    }
    return this.http.get<any>(`${environment.beApiUrl}/user/get-profile`, this.httpOptions).pipe(
      tap(data => this.profileDataSubject.next(data))
    );
  }

  updateProfile(profileData: {
    fullName: string;
    phone: string;
    dob: string;
    address: string;
    imageUrl: string;
  }): Observable<any> {
    const url = `${environment.beApiUrl}/user/update-profile`;
    return this.http.put<any>(url, profileData, this.httpOptions).pipe(
      tap(() => {
        // If the request is successful, update the BehaviorSubject
        this.profileDataSubject.next(profileData);
      }),
      catchError(error => {
        // Handle the error, log it, and rethrow or return a new Observable
        console.error(error);
        return throwError(error);
      })
    );
  }

  getProfileData(): Observable<any> {
    return this.profileDataSubject.asObservable();
  }
}
