import { Parroquia } from './parroquia';
import { Usuario } from './usuario';

export class DatoEncuestado{
    constructor(
        public _id:number,
        public segundoNombre:string,
        public segundoApellido:string,
        public cedula:string,
        public medioConexion:string,
        public edad:any,
        public genero:string,
        public nivel_economico:number,
        public nivelAcademico:string,
        public personasHogar:string,
        public fk_lugar:Parroquia,
        public usuario:Usuario,
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}