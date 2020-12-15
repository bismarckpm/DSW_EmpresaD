import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { Encuesta } from '@core/models/encuesta';

@Component({
  selector: 'app-encuestas-pendientes',
  templateUrl: './encuestas-pendientes.component.html',
  styleUrls: ['./encuestas-pendientes.component.css']
})
export class EncuestasPendientesComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
  ){}

  enc:string="";
  searchState:string="";
  encuestas:Encuesta[]=[];

  dataSource : MatTableDataSource<Encuesta>;

  displayedColumns: string[] = ['id','selector','ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  ngOnInit(): void {}

  setEnc(t:string){
  	if(t!==''){
      this.invokeSearch(t);
    }
    this.enc=t;
  }

  onDirEncuesta(_route:string,Id:number):void {
    try{
    this.router.navigate([`encuestado/survey/${Id}`]);
    }catch(e){
      console.log(e.message);
    }
  }


  invokeSearch(toSearch){ 
    this.searchState="P";
    setTimeout(()=>{
      this.encuestas=[{
        _id:Math.floor(Math.random()*(1000-1)+1),
         activo:true,
         creado_el:new Date(),
         modificado_el:new Date(),
      }]
      this.dataSource = new MatTableDataSource<Encuesta>(this.encuestas);
      this.searchState="D";
    },3000);
  }

}
