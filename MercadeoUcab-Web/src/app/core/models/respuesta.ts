import { Estudio } from './estudio';
import { Opcion } from './opcion';
import { Pregunta } from './pregunta';
import { Usuario } from './usuario';

export class Respuesta {
  constructor(
    //REVISAR
    public _id: number,
    public respuesta: string,
    public opcion: Opcion,
    public usuario: Usuario,
    public Pregunta: Pregunta,
    public estudio: Estudio,
  ) {}
}
