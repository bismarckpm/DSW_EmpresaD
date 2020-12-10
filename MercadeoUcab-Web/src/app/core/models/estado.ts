import { Pais } from './pais';

export class Estado{
    constructor(
        public _id:number,
        public nombre:string,
        public fk_pais: Pais,
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}