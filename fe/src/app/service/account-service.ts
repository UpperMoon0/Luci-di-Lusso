import { Injectable } from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, BehaviorSubject, throwError} from "rxjs";
import {catchError, tap, map} from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  isLoggedIn = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient, private router: Router) {
    this.checkToken();
  }

  login(formData: any): Observable<any> {
    return this.http.post('http://localhost:8081/auth/login', formData).pipe(
      tap(res => {
        if (res && res.accessToken) {
          this.isLoggedIn.next(true);
          localStorage.setItem('accessToken', res.accessToken);
          this.router.navigate(['/home-page']).then(r => {});
        }
      })
    );
  }

  register(formData: any): Observable<any> {
    return this.http.post('http://localhost:8081/auth/register', formData);
  }

  logout(): void {
    this.isLoggedIn.next(false);
    localStorage.removeItem('accessToken');
    this.router.navigate(['/home-page']).then(r => {});
  }

  checkToken(): void {
    const token = localStorage.getItem('accessToken');
    if (token) {
      this.validateToken(token).subscribe({
        next: (res) => {
          if (res.message === 'Valid token') {
            this.isLoggedIn.next(true);
          } else {
            this.isLoggedIn.next(false);
            localStorage.removeItem('accessToken');
          }
        },
        error: () => {
          this.isLoggedIn.next(false);
          localStorage.removeItem('accessToken');
        }
      });
    }
  }

  private validateToken(token: string): Observable<any> {
    return this.http.post('http://localhost:8081/auth/validate-token', token);
  }
}
