import { Component, signal, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { PrendaService } from '../../core/services/prenda.service';
import { CategoriaService } from '../../core/services/categoria.service';
import { Prenda } from '../../core/models/prenda.model';
import { Categoria } from '../../core/models/categoria.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent {
  private prendaService = inject(PrendaService);
  private categoriaService = inject(CategoriaService);

  prendas = signal<Prenda[]>([]);
  categorias = signal<Categoria[]>([]);

  readonly estatusItems = [
    { label: 'En Bodega',   key: 'EN_BODEGA',   color: 'secondary' },
    { label: 'Disponible',  key: 'DISPONIBLE',  color: 'success'   },
    { label: 'Reservado',   key: 'RESERVADO',   color: 'warning'   },
    { label: 'Vendido',     key: 'VENDIDO',     color: 'danger'    },
  ];

  constructor() {
    this.prendaService.getAll().subscribe(data => this.prendas.set(data));
    this.categoriaService.getAll().subscribe(data => this.categorias.set(data));
  }

  count(estatus: string): number {
    return this.prendas().filter(p => p.estatus === estatus).length;
  }

  totalStock(): number {
    return this.prendas().reduce((acc, p) => acc + (p.stock ?? 0), 0);
  }
}
