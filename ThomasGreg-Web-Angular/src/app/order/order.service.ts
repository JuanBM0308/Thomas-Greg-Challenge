import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private baseUrl = 'http://localhost:8080/thomasgreg/order';

  constructor(
    private http: HttpClient
  ) { }

  getDiscounts(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/discounts`).pipe(
      map(value => value as any[])
    );
  }

  orderDetails(id: number): Observable<{ response: any[] }> {
    return this.http.get<{ response: any[] }>(`${this.baseUrl}/get/details/${id}`).pipe(
      map(value => value as { response: any[] })
    );
  }
}
