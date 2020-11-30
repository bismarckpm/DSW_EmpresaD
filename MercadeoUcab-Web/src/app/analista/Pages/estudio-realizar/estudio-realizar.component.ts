import { Component, OnInit } from '@angular/core';
import { EstudioModel } from 'src/app/shared/Models/Estudio.model';
import { EstudioService } from '../../../shared/Services/estudio/estudio.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
@Component({
  selector: 'app-estudio-realizar',
  templateUrl: './estudio-realizar.component.html',
  styleUrls: ['./estudio-realizar.component.css'],
  //providers:[EstudioService]
})
export class EstudioRealizarComponent implements OnInit {
  _Estudio : EstudioModel;
  _Id:number = 0;
  constructor(
    private route: ActivatedRoute,
    private service : EstudioService
    ){}

  ngOnInit(): void {
    console.log(this.route.snapshot.paramMap.get('id'));
    this._Id = parseInt(this.route.snapshot.paramMap.get('id'),10);
    if(this._Id !== 0){
      this._Estudio=this.service.getEstudio(this._Id);
    }
  }

}
