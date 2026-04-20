import { Component, signal, inject } from '@angular/core';
import { DecimalPipe } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { PrendaService } from '../../../core/services/prenda.service';
import { Prenda } from '../../../core/models/prenda.model';

@Component({
  selector: 'app-prenda-detail',
  standalone: true,
  imports: [RouterLink, DecimalPipe],
  templateUrl: './prenda-detail.component.html',
})
export class PrendaDetailComponent {
  private prendaService = inject(PrendaService);
  private route = inject(ActivatedRoute);

  prenda = signal<Prenda | null>(null);
  precioVenta = signal<number | null>(null);
  loading = signal(true);
  error = signal('');

  constructor() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.prendaService.getById(id).subscribe({
      next: data => {
        this.prenda.set(data);
        this.loading.set(false);
        this.prendaService.getPrecio(id).subscribe(precio => this.precioVenta.set(precio));
      },
      error: () => { this.error.set('Prenda no encontrada'); this.loading.set(false); },
    });
  }

  estatusLabel(estatus: string): string {
    const map: Record<string, string> = {
      EN_BODEGA: 'En Bodega', DISPONIBLE: 'Disponible',
      RESERVADO: 'Reservado', VENDIDO: 'Vendido',
    };
    return map[estatus] ?? estatus;
  }

  estatusBadge(estatus: string): string {
    const map: Record<string, string> = {
      EN_BODEGA: 'badge-bodega', DISPONIBLE: 'badge-disponible',
      RESERVADO: 'badge-reservado', VENDIDO: 'badge-vendido',
    };
    return map[estatus] ?? 'bg-secondary';
  }

  tipoLabel(tipo: string): string {
    const map: Record<string, string> = {
      PRECIO_NORMAL: 'Precio Normal', TEMPORADA: 'Temporada', LIQUIDACION: 'Liquidación',
    };
    return map[tipo] ?? tipo;
  }
}
