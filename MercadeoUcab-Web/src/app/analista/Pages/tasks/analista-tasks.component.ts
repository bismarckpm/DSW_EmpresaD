import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { EstudioModel } from './../../../shared/Models/Estudio.model';
import { Router, ActivatedRoute } from '@angular/router';
import { EstudioService } from '@core/services/estudio/estudio.service';
import {MatTableDataSource} from '@angular/material/table';
import { Estudio } from '@models/estudio';

export interface PeriodicElement {
  name: string;
  state:string;
}

@Component({
  selector: 'app-analista-tasks',
  templateUrl: './analista-tasks.component.html',
  styleUrls: ['./analista-tasks.component.css'],
})
export class AnalistaTasksComponent implements OnInit {
  displayedColumns: string[] = ['id','expect','estado'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  estudios: Estudio[] = [];
  dataSource : MatTableDataSource<Estudio>;
  analistaId: number = 5;
  searchState:string="U";
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private _estudioService: EstudioService) {}

  ngOnInit(): void {
    //SERVICE INVOKE
    this.invokeService();
  }
  dataFilter(dataArray:Estudio[]) :Estudio[]{
    let filtered: Estudio[] = [];
    dataArray.forEach((res,ind) => {
      /*if(res._id === this.analistaId){
        filtered.push(res);
      }*/
      filtered.push(res);
    });
    console.log(dataArray,filtered);
    return filtered;
  }
  invokeService(){
    this.searchState="I";
    setTimeout(() => {
      for (let i = 0; i < Math.floor(Math.random()*(100-1)+1); i++) {
        /*this.estudios.push({
         _id:Math.floor(Math.random()*(1000-1)+1),
         estado:(Math.floor(Math.random()*(100-1)+1)%2 === 0)?'I':'P',
         tipo:'A',
         encuestas_esperadas:Math.floor(Math.random()*(100-1)+1)
        });*/
      }
      this.dataSource = new MatTableDataSource<Estudio>(this.dataFilter(this.estudios));
      this.searchState="D";
    },3000)
  }

  onDirEstudio(_route:string,Id:number):void {
    try{
    this.router.navigate([`analist/estudio/${Id}`]);
    }catch(e){
      console.log(e.message);
    }
  }
}
