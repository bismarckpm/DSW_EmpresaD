export class Telefono{
    constructor(
        public _id:number,
        public telefono:string,
        //Falta relacion con Dato Encuestado
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}