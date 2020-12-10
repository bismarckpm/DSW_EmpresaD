import { Solicitud } from './solicitud';
import { Tipo } from './tipo';

export class TipoSolicitud{
    constructor(
        public _id:number,
        public solicitud:Solicitud,
        public tipo: Tipo,
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}