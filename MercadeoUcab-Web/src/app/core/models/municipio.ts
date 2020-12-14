import { Estado } from './estado';

export class Municipio{
    constructor(
        public _id:number,
        public nombre:string,
        public estado: Estado
    ){}
}