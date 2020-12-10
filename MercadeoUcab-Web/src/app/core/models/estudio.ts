import { MuestraPoblacion } from './muestraPoblacion';
import { Solicitud } from './solicitud';
import { Usuario } from './usuario';

export class Estudio {
  constructor(
    public _id: number,
    public estado: string,
    public tipo: string,
    public encuestasEsperadas: number,
    public solicitud: Solicitud,
    public fk_usuario: Usuario,
    public fk_muestra_poblacion: MuestraPoblacion,
    public activo: boolean,
    public creado_el: any,
    public modificado_el: any
  ) {}
}
