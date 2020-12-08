import { Solicitud } from './solicitud';
import { SubCategoria } from './subcategoria';

export class SubCategoriaSolicitud{
    constructor(
        public _id::number,
        public solicitud: Solicitud,
        public subCategoria: SubCategoria,
        public estado:string,
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}