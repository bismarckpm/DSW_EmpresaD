import { Hijo } from './hijo';
import { Parroquia } from './parroquia';
import { Telefono } from './telefono';
import { Usuario } from './usuario';

export class DatoEncuestado {
  constructor(
    public _id: number,
    public cedula: string,
    public medioConexion: string,
    public edad: number,
    public genero: string,
    public nivel_economico: number,
    public nivelAcademico: string,
    public personasHogar: number,
    public parroquia: Parroquia,
    public usuario: Usuario,
    public telefonos: Array<Telefono>,
    public hijos: Array<Hijo>,
    public segundoNombre?: string,
    public segundoApellido?: string
  ) {}
}
