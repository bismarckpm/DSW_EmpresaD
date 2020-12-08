import { Marca } from './marca';
import { Usuario } from './usuario';

export class Solicitud{
    constructor(
        public _id:number,
        public estado:string,
        public usuario: Usuario,
        public marca: Marca,
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}