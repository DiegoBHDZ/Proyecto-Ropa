import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Prenda } from '../models/prenda.model';

@Injectable({ providedIn: 'root' })
export class PrendaService {
  private readonly api = 'http://localhost:8080/api/prendas';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Prenda[]> {
    return this.http.get<Prenda[]>(this.api);
  }

  getById(id: number): Observable<Prenda> {
    return this.http.get<Prenda>(`${this.api}/${id}`);
  }

  getPrecio(id: number): Observable<number> {
    return this.http.get<number>(`${this.api}/${id}/precio`);
  }

  create(prenda: Prenda): Observable<Prenda> {
    return this.http.post<Prenda>(this.api, prenda);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
