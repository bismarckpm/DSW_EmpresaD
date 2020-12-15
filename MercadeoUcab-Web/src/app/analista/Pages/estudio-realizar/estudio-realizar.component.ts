import { Component, OnInit } from '@angular/core';
//import { EstudioModel } from 'src/app/shared/Models/Estudio.model';
//import { EstudioService } from '../../../shared/Services/estudio/estudio.service';
import { EstudioService } from '@core/services/estudio/estudio.service';
import { Muestra_poblacionService } from '@core/services/muestra_poblacion/muestra_poblacion.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { MuestraPoblacion } from '@models/muestraPoblacion';
import { Estudio } from '@models/estudio';
@Component({
  selector: 'app-estudio-realizar',
  templateUrl: './estudio-realizar.component.html',
  styleUrls: ['./estudio-realizar.component.css'],
  providers:[]
})
export class EstudioRealizarComponent implements OnInit {
  _Estudio: Estudio;
  _poblacion: MuestraPoblacion;
  _Id:number = 0;
  constructor(
    private route: ActivatedRoute,
    private _estudioService: EstudioService,
    private _poblacionService: Muestra_poblacionService
    ){}

  ngOnInit(): void {
    console.log(this.route.snapshot.paramMap.get('id'));
    this._Id = parseInt(this.route.snapshot.paramMap.get('id'),10);
    if(this._Id !== 0){
      /*this._Estudio= {
        _id:Math.floor(Math.random()*(1000-1)+1),
         estado:(Math.floor(Math.random()*(100-1)+1)%2 === 0)?'I':'P',
         tipo:'A',
         encuestas_esperadas:Math.floor(Math.random()*(100-1)+1),
      }*/
    }
  }

}
