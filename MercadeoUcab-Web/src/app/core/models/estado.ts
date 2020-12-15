import { Pais } from './pais';

export class Estado{
    constructor(
        public _id:number,
        public nombre:string,
        public pais: Pais,
    ){}
}