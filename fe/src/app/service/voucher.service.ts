import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable} from "rxjs";
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class VoucherService {
  private apiUrl = environment.beApiUrl;

  constructor(private http: HttpClient) {
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
}
