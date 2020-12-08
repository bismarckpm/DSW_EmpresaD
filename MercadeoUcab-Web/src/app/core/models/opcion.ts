export class Opcion{
    constructor(
        public _id:number,
        public nombre_opcion:string,
        // Falta relacion con pregunta
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}