import { Estudio } from './estudio';
import { Pregunta } from './pregunta';
import { Respuesta } from './respuesta';

export class Encuesta {
  constructor(
    public _id: number,
    public respuestas: Array<Respuesta>,
    public pregunta: Pregunta,
    public estudio?: Estudio
  ) {}
}
