import { Tipo } from './tipo';

export class Presentacion {
  constructor(
    public _id: number,
    public Cantidad: string,
    public tipo: string,
    public fk_tipo: Tipo
  ) {}
}
