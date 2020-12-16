import { Injectable } from '@angular/core';
import { EstudioModel } from '../../Models/Estudio.model';

@Injectable({
  providedIn: 'root'
})
export class EstudioService {
  _estudios : EstudioModel[]  =  [
    { Id: Math.floor(Math.random()*(100-1)+1),nombre: 'Hydrogen', 
    fecha_asig:new Date(),estado:'I',poblacion:null,preguntas:null},
    { Id: Math.floor(Math.random()*(100-1)+1),nombre: 'Helium', 
    fecha_asig:new Date(),estado:'A',poblacion:null,preguntas:null},
    { Id: Math.floor(Math.random()*(100-1)+1),nombre: 'Lithium',
    fecha_asig:new Date(),estado:'I',poblacion:null,preguntas:null},
    { Id: Math.floor(Math.random()*(100-1)+1),nombre: 'Beryllium', 
    fecha_asig:new Date(),estado:'A',poblacion:null, preguntas:null},
]; ;
  constructor() {}
  
  getEstudios(): EstudioModel[]  {
    let res: EstudioModel[] = new Array<EstudioModel>();
    res= this._estudios;
    return res;
  }

  getEstudio (estudioId:number): EstudioModel {
    let res: EstudioModel;
    this._estudios.forEach((est,ind) =>{
      if(est.Id === estudioId){
        res = est;
        res.poblacion =[];
        res.preguntas = [];
        const tope = Math.floor(Math.random()*(100-1)+1);
        for (let i = 0; i < tope; i++) {
          res.poblacion.push(
            {
              id: Math.floor(Math.random()*(100-1)+1),
              done:(Math.floor(Math.random()*(100-1)+1) > tope/2 )?true:false
            }
          );
          res.preguntas.push({
            id: Math.floor(Math.random()*(100-1)+1),
            estado:(Math.floor(Math.random()*(100-1)+1) > tope/2 )?'A':'I',
            descripcion:'something'
          })
        }
      }
    });
    console.log(res);
    return res;
  }
  updateEstudio() {

  }

  dateFormat(_D:Date): string {
    return `${_D.getDay()} / ${_D.getMonth()} / ${_D.getFullYear()}`;
  } 
}
