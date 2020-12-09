export class Ocupacion{
    constructor(
        public _id:number,
        public nombre:string,
        // RELACIONES CON DATOS Y POBLACION MUESTRA
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}