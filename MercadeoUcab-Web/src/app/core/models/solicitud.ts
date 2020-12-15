import { Marca } from './marca';
import { Presentacion } from './presentacion';
import { SubCategoria } from './subcategoria';
import { Tipo } from './tipo';
import { Usuario } from './usuario';

export class Solicitud{
    constructor(
        public _id:number,
        public estado:string,
        public usuario: Usuario,
        public marca: Marca,
        public tipos: Array<Tipo>,
        public presentaciones: Array<Presentacion>,
        public subcategorias: Array<SubCategoria>
    ){}
}