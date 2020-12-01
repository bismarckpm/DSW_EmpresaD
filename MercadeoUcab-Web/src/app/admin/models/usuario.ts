export class Usuario{
    constructor(
        public nombre:string,
        public apellido:string,
        public rol:string,
        public estado:string,
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}