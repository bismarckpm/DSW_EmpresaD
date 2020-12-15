import { MuestraPoblacion } from './muestraPoblacion';
import { Pregunta } from './pregunta';
import { Solicitud } from './solicitud';
import { Usuario } from './usuario';

export class Estudio {
  constructor(
    public _id: number,
    public estado: string,
    public tipo: string,
    public encuestas_esperadas: number,
    public solicitud: Solicitud,
    public analista: Usuario,
    public muestra_poblacion: MuestraPoblacion,
    public preguntas: Array<Pregunta>
  ) {}
}
