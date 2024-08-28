import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/thomasgreg/user';
  private isAuthenticated = false;

  constructor(
    private router: Router,
    private http: HttpClient
  ) {}

  login(body: any): boolean {
    if (body.mail === 'Example@example.com' && body.password === 'Secure2024') {
      this.isAuthenticated = true;
      this.router.navigate(['/home']);
      return true;
    }
    return false;
  }

  
  /*login(body: { mail: string; password: string }): Observable<boolean> {
    return this.http.post<any>(`${this.baseUrl}/login`, body).pipe(
      map(res => {
        console.log('Inicio usuario ', res);
        if (res.status) {
          this.isAuthenticated = true;
          this.router.navigate(['/home']);
          return true;
        } else {
          return false;
        }
      }),
      catchError(() => {
        // Manejar errores si la solicitud falla
        this.isAuthenticated = false;
        return of(false);
      })
    );
  }
  */

  isLoggedIn(): boolean {
    return this.isAuthenticated;
  }

  logout() {
    this.isAuthenticated = false;
    this.router.navigate(['/login']);
  }
}