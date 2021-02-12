import { Ocupacion } from './ocupacion';
import { Parroquia } from './parroquia';

export class MuestraPoblacion {
  constructor(
    public _id: number,
    public genero: string,
    public nivel_economico: string,
    public nivel_academico: string,
    public rango_edad_inicio: string,
    public rango_edad_fin:string,
    public cantidad_hijos: number,
    public parroquia: Parroquia,
    public Fk_ocupacion:Ocupacion,
  ) {}
}
