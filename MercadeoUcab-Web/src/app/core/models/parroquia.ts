import { Municipio } from './municipio';

export class Parroquia{
    constructor(
        public _id::number,
        public nombre:string,
        public fk_municipio: Municipio,
        public valor_socio_economico: number,
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}