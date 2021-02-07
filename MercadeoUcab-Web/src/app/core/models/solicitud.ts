import { MuestraPoblacion } from './muestraPoblacion';
import { Presentacion } from './presentacion';
import { Usuario } from './usuario';

export class Solicitud {
  constructor(
    public _id: number,
    public estado: string,
    public comentarios: string,
    public usuario: Usuario,
    public presentaciones: Array<Presentacion>,
    public muestraPoblacion: MuestraPoblacion
  ) {}
}
