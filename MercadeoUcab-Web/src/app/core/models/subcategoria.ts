import { Categoria } from './categoria';

export class SubCategoria{
    constructor(
        public _id:number,
        public nombre:string,
        public categoria: Categoria, 
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}