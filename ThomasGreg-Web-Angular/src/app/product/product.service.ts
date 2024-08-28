import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private baseUrl = 'http://localhost:8080/thomasgreg/product';

  constructor(
    private http: HttpClient
  ) { }

  getProducts(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/all`).pipe(
      map(value => value as any[])
    );
  }
}
