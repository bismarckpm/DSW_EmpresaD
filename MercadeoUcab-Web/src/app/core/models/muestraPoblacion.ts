import { Parroquia } from './parroquia';

export class MuestraPoblacion {
  constructor(
    public _id: number,
    public genero: string,
    public nivelEconomico: number,
    public nivelAcademico: string,
    public rangoEdadInicio: number,
    public rangoEdadFin: number,
    public cantidadHijos: number,
    public fk_lugar: Parroquia,
    //Relacion con ocupacion
    public activo: boolean,
    public creado_el: any,
    public modificado_el: any
  ) {}
}
