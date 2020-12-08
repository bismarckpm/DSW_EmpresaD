import { Categoria } from './categoria';

export class SubCategoria{
    constructor(
        public id_sub_categoria:number,
        public nombre:string,
        public categoria: Categoria, 
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}