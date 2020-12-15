import { Municipio } from './municipio';

export class Parroquia{
    constructor(
        public _id:number,
        public nombre:string,
        public municipio: Municipio,
        public valorSocioEconomico: number
    ){}
}