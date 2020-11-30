import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { EstudioModel } from './../../../shared/Models/Estudio.model';
import { Router, ActivatedRoute } from '@angular/router';
import { EstudioService } from '../../../shared/Services/estudio/estudio.service';
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
  displayedColumns: string[] = ['nombre','fecha_asig','estado'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  data: EstudioModel[] = this.service.getEstudios();
  
  constructor( 
    private route: ActivatedRoute,
    private router: Router,
    private service : EstudioService) {}

  ngOnInit(): void {}

  onDirEstudio(_route:string,Id:number):void {
    try{
    this.router.navigate([`analist/estudio/${Id}`]);
    }catch(e){
      console.log(e.message);
    }
  }
}
