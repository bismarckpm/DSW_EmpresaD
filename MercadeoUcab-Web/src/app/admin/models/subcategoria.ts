export class SubCategoria{
    constructor(
        public id_sub_categoria:number,
        public nombre:string,
        //FALTA RELACION CON CATEGORIA 
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}