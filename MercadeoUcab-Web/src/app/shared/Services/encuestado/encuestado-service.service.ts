import { Injectable } from '@angular/core';
import { EncuestadoModel } from '../../Models/Encuestado.model';

@Injectable({
  providedIn: 'root'
})
export class EncuestadoServiceService {

  constructor() { }

  getEncuestado(enc_id:number): EncuestadoModel | null{
    return null;
  }

  getEncuestados(estudio_id:number): EncuestadoModel | null{
    return null;
  }

  updateEncuestado(enc_id):void {
    
  }
}
