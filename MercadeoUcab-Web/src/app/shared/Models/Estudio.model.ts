import { EncuestadoModel } from '../Models/Encuestado.model';
import { PreguntaModel } from './Pregunta.model';

export class EstudioModel {
    Id:number;
    nombre:string;
    fecha_asig:Date;
    estado:string;
    poblacion:EncuestadoModel[] | null;
    preguntas:PreguntaModel[] | null;
}