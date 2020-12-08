export class Estudio{
    constructor(
        public _id:number,
        public estado:string,
        public tipo:string,
        public encuestas_esperadas:number,
        // FALTAN RELACIONES ESPERAR A COMO LLEGAN DEL BACK
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}