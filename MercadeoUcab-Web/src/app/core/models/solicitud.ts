export class Solicitud{
    constructor(
        public id_solicitud:number,
        public estado:string,
        // Faltan relaciones 
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}