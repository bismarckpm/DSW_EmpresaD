import { Categoria } from './categoria';

export class SubCategoria{
    constructor(
        public _id:number,
        public nombre:string,
        public categoria: Categoria
    ){}
}