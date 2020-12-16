export class Usuario{
    constructor(
        public _id:number,
        public nombre:string,
        public apellido:string,
        public rol:string,
        public correo:string,
        public estado:string
    ){}
}