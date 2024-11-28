import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrl = 'http://localhost:8083/users';
  constructor(
    private http: HttpClient,
  ) { }

  findAllusers(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
