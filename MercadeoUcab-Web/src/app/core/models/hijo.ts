import { DatoEncuestado } from './datoEncuestado';

export class Hijo{
    constructor(
        public _id:number,
        public genero:string,
        public edad: any,
        public fk_dato_encuestado: DatoEncuestado,
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}