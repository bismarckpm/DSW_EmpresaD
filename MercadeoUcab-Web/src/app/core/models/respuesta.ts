import { Opcion } from './opcion';
import { Usuario } from './usuario';

export class Respuesta {
  constructor(
    public _id: number,
    public respuesta: string,
    public dtoopcion: Opcion,
    //relacion con encuesta
    public Dtousuario: Usuario,
    public activo: boolean,
    public creado_el: any,
    public modificado_el: any
  ) {}
}
