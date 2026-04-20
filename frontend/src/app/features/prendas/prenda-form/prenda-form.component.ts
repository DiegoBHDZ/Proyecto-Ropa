import { Component, signal, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { PrendaService } from '../../../core/services/prenda.service';
import { CategoriaService } from '../../../core/services/categoria.service';
import { Categoria } from '../../../core/models/categoria.model';
import { EstatusPrenda, TipoPrecio } from '../../../core/models/prenda.model';

@Component({
  selector: 'app-prenda-form',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './prenda-form.component.html',
})
export class PrendaFormComponent {
  private prendaService = inject(PrendaService);
  private categoriaService = inject(CategoriaService);
  private router = inject(Router);

  categorias = signal<Categoria[]>([]);
  loading = signal(false);
  error = signal('');
  fieldErrors = signal<Record<string, string>>({});

  sku = '';
  nombre = '';
  talla = '';
  color = '';
  material = '';
  stock: number | null = null;
  precioCompra: number | null = null;
  estatus: EstatusPrenda = 'EN_BODEGA';
  tipoPrecio: TipoPrecio = 'PRECIO_NORMAL';
  categoriaId: number | null = null;

  readonly estatusOptions: { label: string; value: EstatusPrenda }[] = [
    { label: 'En Bodega',  value: 'EN_BODEGA'  },
    { label: 'Disponible', value: 'DISPONIBLE' },
    { label: 'Reservado',  value: 'RESERVADO'  },
    { label: 'Vendido',    value: 'VENDIDO'    },
  ];

  readonly tipoPrecioOptions: { label: string; value: TipoPrecio; pct: string }[] = [
    { label: 'Precio Normal', value: 'PRECIO_NORMAL', pct: '+50%' },
    { label: 'Temporada',     value: 'TEMPORADA',     pct: '+80%' },
    { label: 'Liquidación',   value: 'LIQUIDACION',   pct: '+10%' },
  ];

  constructor() {
    this.categoriaService.getAll().subscribe(data => this.categorias.set(data));
  }

  guardar(): void {
    this.error.set('');
    this.fieldErrors.set({});

    if (!this.categoriaId) { this.error.set('Selecciona una categoría'); return; }

    this.loading.set(true);
    this.prendaService.create({
      sku: this.sku,
      nombre: this.nombre,
      talla: this.talla,
      color: this.color || undefined,
      material: this.material || undefined,
      stock: this.stock ?? 0,
      precioCompra: this.precioCompra ?? undefined,
      estatus: this.estatus,
      tipoPrecio: this.tipoPrecio,
      categoria: { id: this.categoriaId, nombre: '', descripcion: '' },
    }).subscribe({
      next: () => this.router.navigate(['/prendas']),
      error: err => {
        this.loading.set(false);
        const msg: string = err?.error?.mensaje ?? err?.error ?? '';
        if (typeof err?.error === 'object' && err?.error?.errores) {
          this.fieldErrors.set(err.error.errores);
        } else {
          this.error.set(msg || 'Error al guardar la prenda');
        }
      },
    });
  }
}
