import { Component, EventEmitter, Output, signal, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CategoriaService } from '../../../core/services/categoria.service';

@Component({
  selector: 'app-categoria-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './categoria-form.component.html',
})
export class CategoriaFormComponent {
  @Output() saved = new EventEmitter<void>();
  @Output() cancelled = new EventEmitter<void>();

  private categoriaService = inject(CategoriaService);

  nombre = '';
  descripcion = '';
  loading = signal(false);
  error = signal('');

  guardar(): void {
    this.error.set('');
    this.loading.set(true);
    this.categoriaService.create({ nombre: this.nombre, descripcion: this.descripcion }).subscribe({
      next: () => { this.loading.set(false); this.saved.emit(); },
      error: err => {
        this.loading.set(false);
        this.error.set(err?.error?.mensaje ?? 'Error al guardar la categoría');
      },
    });
  }
}
