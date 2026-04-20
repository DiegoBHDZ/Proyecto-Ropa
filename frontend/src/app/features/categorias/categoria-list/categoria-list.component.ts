import { Component, signal, inject } from '@angular/core';
import { CategoriaService } from '../../../core/services/categoria.service';
import { Categoria } from '../../../core/models/categoria.model';
import { CategoriaFormComponent } from '../categoria-form/categoria-form.component';

@Component({
  selector: 'app-categoria-list',
  standalone: true,
  imports: [CategoriaFormComponent],
  templateUrl: './categoria-list.component.html',
})
export class CategoriaListComponent {
  private categoriaService = inject(CategoriaService);

  categorias = signal<Categoria[]>([]);
  loading = signal(true);
  showForm = signal(false);

  constructor() {
    this.cargar();
  }

  cargar(): void {
    this.loading.set(true);
    this.categoriaService.getAll().subscribe({
      next: data => { this.categorias.set(data); this.loading.set(false); },
      error: () => this.loading.set(false),
    });
  }

  onSaved(): void {
    this.showForm.set(false);
    this.cargar();
  }
}
