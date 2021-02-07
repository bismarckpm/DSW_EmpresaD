import { SubCategoria } from './subcategoria';

export class Tipo {
  constructor(
    public _id: number,
    public nombre: string,
    public subCategoria: SubCategoria
  ) {}
}
