import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', loadComponent: () => import('./features/dashboard/dashboard.component').then(m => m.DashboardComponent) },
  { path: 'prendas', loadComponent: () => import('./features/prendas/prenda-list/prenda-list.component').then(m => m.PrendaListComponent) },
  { path: 'prendas/nueva', loadComponent: () => import('./features/prendas/prenda-form/prenda-form.component').then(m => m.PrendaFormComponent) },
  { path: 'prendas/:id', loadComponent: () => import('./features/prendas/prenda-detail/prenda-detail.component').then(m => m.PrendaDetailComponent) },
  { path: 'categorias', loadComponent: () => import('./features/categorias/categoria-list/categoria-list.component').then(m => m.CategoriaListComponent) },
  { path: '**', redirectTo: '' },
];
