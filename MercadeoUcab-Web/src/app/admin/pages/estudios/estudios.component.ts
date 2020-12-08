import { Component, OnInit, ViewChild } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatTableDataSource} from '@angular/material/table';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';
import { DelEstudioDialogComponent } from '../../components/dialogs/del-estudio-dialog/del-estudio-dialog.component';
import { UpdEstudioDialogComponent } from '../../components/dialogs/upd-estudio-dialog/upd-estudio-dialog.component';
import { Estudio } from '@models/estudio';
import { Marca } from '@models/marca';
import { Pregunta } from '@models/pregunta';
import { Presentacion } from '@models/presentacion';
import { Solicitud } from '@models/solicitud';

@Component({
  selector: 'app-estudios',
  templateUrl: './estudios.component.html',
  styleUrls: ['./estudios.component.css'],
  providers: [{
    provide: STEPPER_GLOBAL_OPTIONS, useValue: {displayDefaultIndicatorType: false}
  }]
})
export class EstudiosComponent implements OnInit {
  op:string;
  searchState:string;//U,I,P,D
  /*searchModel:Usuario;
  users: UserModel[] = [];
  displayedColumns: string[] = ['id','selector','ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  userSelection:number = 0;
  dataSource : MatTableDataSource<UserModel>;*/
  solicitudSelec: number;
  solicitudes:Solicitud[]=[
    /*{ _id:1, estado:'I', activo:false, creado_el:new Date(), modificado_el:new Date()},
    { _id:2, estado:'I', activo:false, creado_el:new Date(), modificado_el:new Date()},
    { _id:3, estado:'A', activo:false, creado_el:new Date(), modificado_el:new Date()},
    { _id:4, estado:'I', activo:false, creado_el:new Date(), modificado_el:new Date()},
    { _id:5, estado:'I', activo:false, creado_el:new Date(), modificado_el:new Date()},
    { _id:6, estado:'I', activo:false, creado_el:new Date(), modificado_el:new Date()}*/
  ];
  estudios: Estudio[] = [
    {_id:1,estado:'A',tipo:'A',encuestas_esperadas:98,activo:true,creado_el:new Date(),modificado_el:new Date()},
    {_id:2,estado:'A',tipo:'A',encuestas_esperadas:3,activo:true,creado_el:new Date(),modificado_el:new Date()},
    {_id:3,estado:'A',tipo:'A',encuestas_esperadas:16,activo:true,creado_el:new Date(),modificado_el:new Date()},
    {_id:4,estado:'A',tipo:'A',encuestas_esperadas:50,activo:true,creado_el:new Date(),modificado_el:new Date()},
  ];
  dataSource : MatTableDataSource<Estudio>;
  userSelection:number = 0

  marcas: Marca[] = [
    /*{_id:1,activo:true,creado_el:new Date(),modificado_el:new Date()},
    {_id:2,activo:true,creado_el:new Date(),modificado_el:new Date()},
    {_id:3,activo:true,creado_el:new Date(),modificado_el:new Date()},*/
  ];
  preguntas:Pregunta[] = [
  {_id:1,nombre_pregunta:'Preg 1',tipo:'simple',rango:'',activo:true,creado_el:new Date(),modificado_el:new Date()},
  {_id:2,nombre_pregunta:'Preg 2',tipo:'simple',rango:'',activo:true,creado_el:new Date(),modificado_el:new Date()},
  {_id:3,nombre_pregunta:'Preg 3',tipo:'simple',rango:'',activo:true,creado_el:new Date(),modificado_el:new Date()},
  {_id:4,nombre_pregunta:'Preg 4',tipo:'simple',rango:'',activo:true,creado_el:new Date(),modificado_el:new Date()},
  {_id:5,nombre_pregunta:'Preg 5',tipo:'simple',rango:'',activo:true,creado_el:new Date(),modificado_el:new Date()},
  {_id:6,nombre_pregunta:'Preg 6',tipo:'simple',rango:'',activo:true,creado_el:new Date(),modificado_el:new Date()},
  ];
  pregAsoc:Pregunta[]=[];
  presentaciones: Presentacion[] = [{
    _id:1,
    tipo:'empaque',
    cantidad:'1x16',
    activo:true,
    creado_el:new Date(),
    modificado_el:new Date()
  }];
  //tipos_sol: TipoSolicitud[] = [];
  displayedColumns: string[] = ['id','selector','ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  updForm;
  addForm;
  searchForm;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  constructor(private modalService: NgbModal,private formBuilder: FormBuilder) { }

  @ViewChild('updEstudio') private updComponent:UpdEstudioDialogComponent;
  async openUpdModal() {
    return await this.updComponent.open();
  }
  @ViewChild('delEstudio') private delComponent:DelEstudioDialogComponent;
  async openDelModal() {
    return await this.delComponent.open();
  }
  ngOnInit(): void {
    this.searchForm = this.formBuilder.group({

    });
    //FORMUALRIO PARA SOLICITUD
    this.addForm = this.formBuilder.group({
      /*estado:'',
      activo:1,
      creado_el:'',
      modificado_el:'',
      fk_usuario:1,
      fk_marca:0,
      fk_subCategoria:0,
      fk_presentacion:0,
      fk_tipoSolicitud:0,
      preguntas:[]*/
      fk_solicitud:0,
    })
    this.setOperation('');
  }
  invokeSearch(){
    //console.log('Search works');
    //console.log(this.searchForm.value);
    setTimeout(()=>{
      this.dataSource = new MatTableDataSource<Estudio>(this.estudios);
      this.searchState="D";
    },3000);
  }
  setOperation(chOp:string){
    this.op=chOp;
    if(chOp !== ''){
      this.searchState="I";
    }
    else{
      this.searchState="U";
    }
  }
  //CONTROL DE SELECCIÓN EN TABLA DE DATOS
  selectUser(id: number){
    if(id === this.userSelection){
      this.userSelection = 0;
    }
    else{
      this.userSelection=id;
    }
  }
  isSelected(id: number):boolean{
    if(id === this.userSelection){
      return true;
    }
    return false;
  }
  doSearch(){
    this.searchState="I";
  }
  stepCheck () {
    console.log(this.addForm.value);
  }
}
