import { Component, signal, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { PrendaService } from '../../../core/services/prenda.service';
import { Prenda } from '../../../core/models/prenda.model';

@Component({
  selector: 'app-prenda-list',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './prenda-list.component.html',
})
export class PrendaListComponent {
  private prendaService = inject(PrendaService);

  prendas = signal<Prenda[]>([]);
  loading = signal(true);
  error = signal('');

  constructor() {
    this.prendaService.getAll().subscribe({
      next: data => { this.prendas.set(data); this.loading.set(false); },
      error: () => { this.error.set('Error al cargar las prendas'); this.loading.set(false); },
    });
  }

  eliminar(id: number): void {
    if (!confirm('¿Eliminar esta prenda?')) return;
    this.prendaService.delete(id).subscribe({
      next: () => this.prendas.update(list => list.filter(p => p.id !== id)),
      error: () => this.error.set('No se pudo eliminar la prenda'),
    });
  }

  estatusBadge(estatus: string): string {
    const map: Record<string, string> = {
      EN_BODEGA: 'badge-bodega', DISPONIBLE: 'badge-disponible',
      RESERVADO: 'badge-reservado', VENDIDO: 'badge-vendido',
    };
    return map[estatus] ?? 'bg-secondary';
  }

  estatusLabel(estatus: string): string {
    const map: Record<string, string> = {
      EN_BODEGA: 'En Bodega', DISPONIBLE: 'Disponible',
      RESERVADO: 'Reservado', VENDIDO: 'Vendido',
    };
    return map[estatus] ?? estatus;
  }

  tipoBadge(tipo: string): string {
    const map: Record<string, string> = {
      PRECIO_NORMAL: 'badge-normal', TEMPORADA: 'badge-temporada', LIQUIDACION: 'badge-liquidacion',
    };
    return map[tipo] ?? 'bg-secondary';
  }

  tipoLabel(tipo: string): string {
    const map: Record<string, string> = {
      PRECIO_NORMAL: 'Normal', TEMPORADA: 'Temporada', LIQUIDACION: 'Liquidación',
    };
    return map[tipo] ?? tipo;
  }
}
