import { Opcion } from './opcion';
import { Usuario } from './usuario';

export class Pregunta {
  constructor(
    public _id: number,
    public nombre: string,
    public tipo: string,
    public rango: string,
    public opciones: Array<Opcion>,
    public usuario: Usuario
  ) {}
}
