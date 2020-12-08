import { Estado } from './estado';

export class Municipio{
    constructor(
        public _id::number,
        public nombre:string,
        public fk_estado: Estado,
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}