import { MuestraPoblacion } from './muestraPoblacion';
import { Solicitud } from './solicitud';
import { surveyEncuesta } from './surveyEncuesta';
import { surveySolicitud } from './surveySolicitud';
import { Usuario } from './usuario';

export class surveyEstudio {
  constructor(
    public _id: number,
    public estado: string,
    public tipo: string,
    public encuestas_esperadas: number,
    public solicitud: surveySolicitud,
    public analista: Usuario,
    public muestra_poblacion: MuestraPoblacion,
    public encuesta: Array<surveyEncuesta>
  ) {}
}
