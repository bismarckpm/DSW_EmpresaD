import {Component, OnInit, ViewChild} from '@angular/core';
import {Solicitud} from "@models/solicitud";
import {MatTableDataSource} from "@angular/material/table";
import {FormBuilder, FormGroup} from "@angular/forms";
import {SolicitudService} from "@core/services/solicitud/solicitud.service";
import {UpdateSolicitudDialogComponent} from "../../components/dialogs/upd-solicitud-dialog/update-solicitud-dialog.component";
import {Estudio} from "@models/estudio";
import {EstudioService} from "@core/services/estudio/estudio.service";

@Component({
  selector: 'app-estudio',
  templateUrl: './estudio.component.html',
  styleUrls: ['./estudio.component.css']
})
export class EstudioComponent implements OnInit {


  //CONTROL DE ESTADO DEL COMPONENTE
  op:string;
  searchState:string;//U.I,D
  estudios: Estudio[] = [];

  //COLUMNAS DE TABLA DE RESULTADOS
  displayedColumns: string[] = ['id','selector'];
  columnsToDisplay: string[] = this.displayedColumns.slice();

  //INDICE DE SOLICITUD SELECCIONADO
  estudioSelection:number = 0;

  //LISTA DE SOLICITUDES DEVUELTOS EN BÚSQUEDA
  dataSource : MatTableDataSource<Estudio>;
  estudioTarget: Estudio;
  //FORMULARIOS
  searchForm:FormGroup;
  searchModel:Estudio;
  addForm:FormGroup;
  opStatus:string;//S,P,D

  constructor(
    //private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _estudioService: EstudioService,
  ) {
    this.searchForm = this.formBuilder.group({
      estado:'activo',
      tipo:null,
      encuestas_esperadas:null,
      activo:null,
      creado_el:null,
      modificado_el:null
    })
  }
  /*getTarget(id:number){
    this.users.forEach((user,ind) => {
      if(user._id === id){

      }
    });
  };*/
  getEstudios(){
    this._estudioService.getEstudios().subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    )
  }

  /*addSolicitudd(data){
    this._estudioService.createSolicitud(data).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    )
  }*/

  ngOnInit(): void {
    this.setOperation('');
    this.searchState="U";
    this.getEstudios();
  }

  serviceInvoke(){
    /*
    "nombre": data.nombre,
    "apellido": data.apellido,
    "estado": data.estado,
    "rol": data.rol,
    "correo": data.correo
    */
    //FALTA VALIDACION
    //console.log(this.addForm.value);
    this.opStatus="P";
    console.log(this.op);
    console.log(this.opStatus);
    console.log(this.opStatus);
    setTimeout(()=>{
      this.addForm = this.formBuilder.group({
        marca: null,
        usuario:null,
        presentacionTipo:null,
        subcategoria:null,
        activo:null,
        estado:'Activo',
      });
      this.opStatus="D";
    },3000);
  }
  selectEstudio(id: number,data:Estudio){
    if(id === this.estudioSelection){
      this.estudioSelection = 0;
      this.estudioTarget=null;
    }
    else{
      this.estudioSelection=id;
      this.estudioTarget=data;
    }
  }
  isSelected(id: number):boolean{
    if(id === this.estudioSelection){
      return true;
    }
    return false;
  }
  dataFilter(dataArray:Estudio[]): Estudio[]{
    console.log(this.searchForm.value);
    let filtered: Estudio[] = [];
    dataArray.forEach((res,ind) => {
      let inc = true;
      Object.entries(this.searchForm.value).forEach(([key,field],_ind)=>{
        if(inc === true && field !== null){
          if(field instanceof Date && (res[key] >= field && res[key] <= Date.now())){
            return;
          }
          else if(typeof(field)==='string' && res[key].startsWith(field)){
            return;
          }
          else if(typeof(field)==='boolean' && res[key]===field){
            return;
          }
          else{
            inc = false;
          }
        }
      })
      if(inc === true){
        filtered.push(res);
      }
    })
    console.log(dataArray,filtered);
    return filtered;
  }
  invokeSearch(){
    this.estudios = [];
    this.estudioSelection=0;
    if(this.searchForm.value['creado_el'] !== null){
      this.searchForm.get('creado_el').setValue(new Date(this.searchForm.value['creado_el']));
    }
    if(this.searchForm.value['modificado_el'] !== null){
      this.searchForm.get('modificado_el').setValue(new Date(this.searchForm.value['modificado_el']));
    }
    //this.searchForm.get('');
    this.searchState="P";
    console.log(this.searchState);
    setTimeout(()=>{
      for (let i = 0; i < Math.floor(Math.random()*(100-1)+1); i++) {
        /*this.estudios.push({
          _id:Math.floor(Math.random()*(100-1)+1),
          estado:"Procesando",
          tipo:"encuesta",
          encuestasEsperadas:Math.floor(Math.random()*(100-1)+1),
          activo:true,
          creado_el:new Date(),
          modificado_el:new Date(),
        });*/
      }
      this.dataSource = new MatTableDataSource<Estudio>(this.dataFilter(this.estudios));
      this.searchState="D";
    },3000);
  }
  setOperation(chOp:string){
    this.op=chOp;
    console.log(this.searchState);
    if(chOp !== ''){
      this.searchState="I";
      this.opStatus="S";
    }
    else{
      this.searchState="U";
    }
  }
  doSearch(){
    this.searchState="I";
  }

}
