import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:8080/thomasgreg/user';

  constructor(
    private http: HttpClient
  ) { }

  // método para obtener todos los usuarios
  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/get/all`).pipe(
      map(value => value as any[])
    );
  }

  // método para crear un usuario
  createUser(body: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/create`, body).pipe(
      map(value => value as any)
    );
  }

  // método para actualizar un usuario
  updateUser(body: any): Observable<any> {    
    return this.http.put<any>(`${this.baseUrl}/update`, body).pipe(
      map(value => value as any)
    );
  }

  // método para eliminar un usuario por ID
  deleteUser(id: number) {
    return this.http.delete<any>(`${this.baseUrl}/delete/${id}`);
  }

  // método para obtener el usuario
  getUserById(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/get/by/${id}`).pipe(
      map(value => value as any)
    );
  }
}