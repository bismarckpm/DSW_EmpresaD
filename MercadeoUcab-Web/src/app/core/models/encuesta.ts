export class Encuesta{
    constructor(
        public id_encuesta:number,
        //RELACION CON PREGUNTA Y ESTUDIO
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}