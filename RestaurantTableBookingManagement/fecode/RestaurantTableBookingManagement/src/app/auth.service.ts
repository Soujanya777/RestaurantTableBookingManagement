import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly authTokenKey = 'auth_token';
  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    const params = new HttpParams()
      .set('email', email)
      .set('password', password);
    return this.http.get(`api/user/login`, { params: params });
  }

  logout(): void {
    localStorage.removeItem(this.authTokenKey);
  }

  getId(): any {
    return localStorage.getItem(this.authTokenKey);
  }

  isLoggedIn(): boolean {
    console.log(!!localStorage.getItem(this.authTokenKey));
    return !!localStorage.getItem(this.authTokenKey);
  }

  storeToken(token: string): void {
    localStorage.setItem(this.authTokenKey, token);
  }
}
