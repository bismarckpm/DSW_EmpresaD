export class Pregunta{
    constructor(
        public id_pregunta:number,
        public nombre_pregunta:string,
        public tipo:string,
        public rango:string,
        // Falta relacion con usuario
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}