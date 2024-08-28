import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  private baseUrl = 'http://localhost:8080/thomasgreg/report';

  constructor(
    private http: HttpClient
  ) { }

  getActiveProducts(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/active/products`).pipe(
      map(value => value as any[])
    );
  }

  getReportProducts(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/top/products`).pipe(
      map(value => value as any[])
    );
  }

  getReportCustomers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/top/customers`).pipe(
      map(value => value as any[])
    );
  }
}
