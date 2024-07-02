import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, BehaviorSubject} from "rxjs";
import {tap} from 'rxjs/operators';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  isLoggedIn = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient, private router: Router) { }

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
}
