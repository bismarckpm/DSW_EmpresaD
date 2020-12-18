import { surveyPregunta } from './surveyPregunta';

export class surveyEncuesta {
  constructor(public _id: number, public pregunta: surveyPregunta) {}
}
