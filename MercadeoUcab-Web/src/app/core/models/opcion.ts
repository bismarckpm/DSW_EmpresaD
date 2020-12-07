export class Opcion{
    constructor(
        public id_opcion:number,
        public nombre_opcion:string,
        // Falta relacion con pregunta
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}