import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Categoria } from '../models/categoria.model';

@Injectable({ providedIn: 'root' })
export class CategoriaService {
  private readonly api = 'http://localhost:8080/api/categorias';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(this.api);
  }

  create(categoria: Categoria): Observable<Categoria> {
    return this.http.post<Categoria>(this.api, categoria);
  }
}
