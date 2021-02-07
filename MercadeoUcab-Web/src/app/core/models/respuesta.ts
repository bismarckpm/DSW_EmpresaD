import { Opcion } from './opcion';
import { Usuario } from './usuario';

export class Respuesta {
  constructor(
    public _id: number,
    public usuario: Usuario,
    public respuesta?: string,
    public opcion?: Opcion
  ) {}
}
