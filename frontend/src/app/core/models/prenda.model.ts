import { Categoria } from './categoria.model';

export type EstatusPrenda = 'EN_BODEGA' | 'DISPONIBLE' | 'RESERVADO' | 'VENDIDO';
export type TipoPrecio = 'PRECIO_NORMAL' | 'TEMPORADA' | 'LIQUIDACION';

export interface Prenda {
  id?: number;
  sku: string;
  nombre: string;
  talla: string;
  color?: string;
  material?: string;
  stock?: number;
  precioCompra?: number;
  estatus?: EstatusPrenda;
  tipoPrecio?: TipoPrecio;
  categoria: Categoria;
}
