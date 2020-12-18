import { Ocupacion } from './ocupacion';
import { Parroquia } from './parroquia';

export class MuestraPoblacion {
  constructor(
    public _id: number,
    public genero: string,
    public nivel_economico: number,
    public nivel_academico: string,
    public rango_edad_inicio: number,
    public rango_edad_fin: number,
    public cantidad_hijos: number,
    public parroquia: Parroquia,
    public Fk_ocupacion:Ocupacion,
  ) {}
}
