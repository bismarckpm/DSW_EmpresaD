import { Component, OnInit, ViewChild } from '@angular/core';
import { Form, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { DelEstudioDialogComponent } from '../../components/dialogs/del-estudio-dialog/del-estudio-dialog.component';
import { UpdEstudioDialogComponent } from '../../components/dialogs/upd-estudio-dialog/upd-estudio-dialog.component';
import { Estudio } from '@models/estudio';
import { Marca } from '@models/marca';
import { Pregunta } from '@models/pregunta';
import { Presentacion } from '@models/presentacion';
import { Solicitud } from '@models/solicitud';
import { EstudioService } from '@core/services/estudio/estudio.service';
import { SolicitudService } from '@core/services/solicitud/solicitud.service';
import { Usuario } from '@core/models/usuario';
import { Muestra_poblacionService } from '@core/services/muestra_poblacion/muestra_poblacion.service';
import { Ocupacion } from '@core/models/ocupacion';
import { Parroquia } from '@core/models/parroquia';
import { ParroquiaService } from '@core/services/parroquia/parroquia.service';
import { Municipio } from '@core/models/municipio';
import { Estado } from '@core/models/estado';
import { Pais } from '@core/models/pais';
import { animate, state, style, transition, trigger } from '@angular/animations';

@Component({
  selector: 'app-estudios',
  templateUrl: './estudios.component.html',
  styleUrls: ['./estudios.component.css'],
  providers: [
    {
      provide: STEPPER_GLOBAL_OPTIONS,
      useValue: { displayDefaultIndicatorType: false },
    },
  ],
  animations:[]
})
export class EstudiosComponent implements OnInit {
  op: string;
  searchState: string; //U,I,P,D
  solicitudSelec: number;
  opStatus: string;
  currentStep : number= 0;
  solicitudes: Solicitud[] = [];
  ocupaciones: Ocupacion[] = [];
  estudios: Estudio[] = [];
  dataSource: MatTableDataSource<Estudio>;
  userSelection: number = 0;

  marcas: Marca[] = [
    /*{_id:1},
    {_id:2},
    {_id:3},*/
  ];
  preguntas: Pregunta[] = [
    /*
  {_id:1,nombre_pregunta:'Preg 1',tipo:'simple',rango:''},
  {_id:2,nombre_pregunta:'Preg 2',tipo:'simple',rango:''},
  {_id:3,nombre_pregunta:'Preg 3',tipo:'simple',rango:''},
  {_id:4,nombre_pregunta:'Preg 4',tipo:'simple',rango:''},
  {_id:5,nombre_pregunta:'Preg 5',tipo:'simple',rango:''},
  {_id:6,nombre_pregunta:'Preg 6',tipo:'simple',rango:''},
  */
  ];
  pregAsoc: Pregunta[] = [];
  presentaciones: Presentacion[] = [
    /*{
      _id: 1,
      tipo: 'empaque',
      cantidad: '1x16',
      activo: true,
      creado_el: new Date(),
      modificado_el: new Date(),
    },*/
  ];

  targetEstudio:Estudio;

  displayedColumns: string[] = ['id', 'selector', 'ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  addForm:FormGroup;
  poblacionForm:FormGroup;
  searchForm:FormGroup;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  parroquias: Parroquia[]=[];
  testPais: Pais = {
    _id: 1,
    nombre: 'Test pais',
  };
   testEstado: Estado = {
    _id: 1,
    nombre: 'Test estado',
    pais: this.testPais,
  };
   testMunicipio: Municipio = {
    _id: 1,
    nombre: 'Test  municipio',
    estado: this.testEstado,
  };
   testParroquia: Parroquia = {
    _id: 1,
    nombre: 'Test  parrroquia',
    municipio:this.testMunicipio,
    valorSocioEconomico: 8000,
  };
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _estudioService: EstudioService,
    private _solicitudService: SolicitudService,
    private _poblacionService: Muestra_poblacionService,
    private _parroquiaService:ParroquiaService,
  ) {}
  testUser: Usuario = {
    _id:Math.floor(Math.random()*(1000-1)+1),
    nombre:Math.random().toString(36).substr(2, 5),
    apellido:Math.random().toString(36).substr(2, 5),
    rol:'Administrador',
    correo:Math.random().toString(36).substr(2, 5),
    estado:'Activo',
  }
  @ViewChild('updEstudio') private updComponent: UpdEstudioDialogComponent;
  async openUpdModal() {
    return await this.updComponent.open();
  }
  @ViewChild('delEstudio') private delComponent: DelEstudioDialogComponent;
  async openDelModal() {
    return await this.delComponent.open();
  }
  ngOnInit(): void {
    this.opStatus = 'S';
    this.searchForm = this.formBuilder.group({});
    this.getSolicitudes();
    //FORMUALRIO PARA SOLICITUD
    /*
  Las preguntas tienen que existir
  {
    "estado":String,
    "tipo":String
    "encuestasEsperadas":int,
    "solicitud"int,
    "fk_usuario":int,
    "fk_muestra_poblacion":int,
    "preguntas":[
      {
        "_id":int
      },
      .
      .
      .
      {
        "_id":int
      }
    ]
  }
  */
    this.addForm = this.formBuilder.group({
      estado:null,
      tipo:null,
      encuestasEsperadas:null,
      fk_usuario:null,
      fk_muestra_poblacion:null,
      solicitud: null,
      preguntas:[],
    });
    this.poblacionForm=this.formBuilder.group({
      /*{
        "genero":"genero",
        "nivelEconomico":int,
        "nivelAcademico":String,
        "rangoEdadInicio":int,
        "rangoEdadFin":int,
        "cantidadHijos":int,
        "fk_lugar": int,
        "fk_ocupacion":int
      }*/
      genero:null,
      nivelEconomico:null,
      nivelAcademico:null,
      rangoEdadInicio:null,
      rangoEdadFin:null,
      cantidadHijos:null,
      fk_lugar:null,
      fk_ocupacion:null,
    });
    this.getOcupaciones();
    this.getParroquias();
    this.setOperation('');
  }
  addPoblacion(data){
    this._poblacionService.createMuestraPoblacion(data).subscribe(
      (response)=>{

      },
      (error) => {

      }
    );
  }
  getParroquias(){
    this._parroquiaService.getParroquias().subscribe(
      (response)=>{
        this.parroquias = response.data;
      },
      (error) => {
        this.parroquias =[this.testParroquia];
      }
    );
  }
  getOcupaciones(){
    this.ocupaciones=[
      {_id:1,nombre:'Ocupacion Test'},
    ]
  }
  getEstudios() {
    this._estudioService.getEstudios().subscribe(
      (response) => {
        console.log(response);
        this.estudios = response.data;
        this.dataSource = new MatTableDataSource<Estudio>(this.estudios);
        this.searchState="D";
      },
      (error) => {
        console.log(error);
        this.estudios = [];
        this.dataSource = new MatTableDataSource<Estudio>(this.estudios);
        this.searchState="D";
      }
    );
  }

  addEstudio(data) {
    this._estudioService.createEstudio(data).subscribe(
      (response) => {
        console.log(response);
        alert('Se agrego el estudio correctamente');
        this.opStatus="D";
      },
      (error) => {
        console.log(error);
        this.opStatus="E";
      }
    );
  }

  updateEstudio(id, data) {
    this._estudioService.updateEstudio(id, data).subscribe(
      (response) => {
        console.log(response);
        alert('Se modifico el estudio correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }

  deleteEstudio(id, data) {
    this._estudioService.deleteEstudio(id, data).subscribe(
      (response) => {
        console.log(response);
        alert('Se elimino el estudio correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getSolicitudes() {
    this._solicitudService.getSolicitudes().subscribe(
      (response) => {
        console.log(response);
        this.solicitudes = response.data;
      },
      (error) => {
        console.log(error);
        this.solicitudes = [{
         _id:13,
         estado:'activo',
         usuario: this.testUser,
         marca: {_id:1,nombre:'TEST MARCA'},
         tipos: [{_id:1,nombre:'test Tipo'}],
         presentaciones: [{_id:1,tipo:'volumen',cantidad:'800ml'}],
         subcategorias:[{_id:1,nombre:'test SubCategoria',categoria:{_id:1,nombre:'test Categoria'}}]
        },
        {
          _id:34,
          estado:'activo',
          usuario: this.testUser,
          marca: {_id:1,nombre:'TEST MARCA'},
          tipos: [{_id:1,nombre:'test Tipo'}],
          presentaciones: [{_id:1,tipo:'volumen',cantidad:'800ml'}],
          subcategorias:[{_id:1,nombre:'test SubCategoria',categoria:{_id:1,nombre:'test Categoria'}}]
         },
         {
          _id:107,
          estado:'activo',
          usuario: this.testUser,
          marca: {_id:1,nombre:'TEST MARCA'},
          tipos: [{_id:1,nombre:'test Tipo'}],
          presentaciones: [{_id:1,tipo:'volumen',cantidad:'800ml'}],
          subcategorias:[{_id:1,nombre:'test SubCategoria',categoria:{_id:1,nombre:'test Categoria'}}]
         },
         {
          _id:113,
          estado:'activo',
          usuario: this.testUser,
          marca: {_id:1,nombre:'TEST MARCA'},
          tipos: [{_id:1,nombre:'test Tipo'}],
          presentaciones: [{_id:1,tipo:'volumen',cantidad:'800ml'}],
          subcategorias:[{_id:1,nombre:'test SubCategoria',categoria:{_id:1,nombre:'test Categoria'}}]
         },
         {
          _id:12,
          estado:'activo',
          usuario: this.testUser,
          marca: {_id:1,nombre:'TEST MARCA'},
          tipos: [{_id:1,nombre:'test Tipo'}],
          presentaciones: [{_id:1,tipo:'volumen',cantidad:'800ml'}],
          subcategorias:[{_id:1,nombre:'test SubCategoria',categoria:{_id:1,nombre:'test Categoria'}}]
         },
         {
          _id:5,
          estado:'activo',
          usuario: this.testUser,
          marca: {_id:1,nombre:'TEST MARCA'},
          tipos: [{_id:1,nombre:'test Tipo'}],
          presentaciones: [{_id:1,tipo:'volumen',cantidad:'800ml'}],
          subcategorias:[{_id:1,nombre:'test SubCategoria',categoria:{_id:1,nombre:'test Categoria'}}]
         },
         {
          _id:90,
          estado:'activo',
          usuario: this.testUser,
          marca: {_id:1,nombre:'TEST MARCA'},
          tipos: [{_id:1,nombre:'test Tipo'}],
          presentaciones: [{_id:1,tipo:'volumen',cantidad:'800ml'}],
          subcategorias:[{_id:1,nombre:'test SubCategoria',categoria:{_id:1,nombre:'test Categoria'}}]
         }
      ];
      }
    );
  }
/*
  addSolicitud(data) {
    this._solicitudService.createSolicitud(data).subscribe(
      (response) => {
        console.log(response);
        alert('Se agrego la solicitud correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }

  updateSolicitud(id, data) {
    this._solicitudService.updateSolicitud(id, data).subscribe(
      (response) => {
        console.log(response);
        alert('Se modifico la solicitud correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }

  deleteSolicitud(id, data) {
    this._solicitudService.deleteSolicitud(id, data).subscribe(
      (response) => {
        console.log(response);
        alert('Se elimino la solicitud correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }
*/
  invokeService() {
    
  }

  invokeSearch() {
    this.estudios = [];
    this.userSelection = 0;
    let toAdd: any = {...this.searchForm.value};
    this.getEstudios();
    this.searchState = 'P';
    /*setTimeout(() => {
      this.dataSource = new MatTableDataSource<Estudio>(this.estudios);
      this.searchState = 'D';
    }, 3000);*/
  }
  setOperation(chOp: string) {
    this.op = chOp;
    if (chOp !== '') {
      this.searchState = 'I';
    } else {
      this.searchState = 'U';
    }
  }
  //CONTROL DE SELECCIÓN EN TABLA DE DATOS
  selectUser(id: number,data:Estudio) {
    if (id === this.userSelection) {
      this.userSelection = 0;
      this.targetEstudio=null;
    } else {
      this.userSelection = id;
      this.targetEstudio=data;
    }
  }
  isSelected(id: number): boolean {
    if (id === this.userSelection) {
      return true;
    }
    return false;
  }
  doSearch() {
    this.searchState = 'I';
  }
  stepCheck(ind,stepper) {
    switch(ind){
      case 0:
        document.getElementById('addStepper').classList.add('leftSlider');
        this.currentStep = 1; 
        stepper.next();
        break;
      case 1:
        document.getElementById('addStepper').classList.remove('leftSlider');
        document.getElementById('addStepper').classList.add('rightSlider');
        document.getElementById('suggests').classList.add('SlideOut');
        this.currentStep = 2; 
        setTimeout(() => {
          //document.getElementById('addStepper').classList.remove('rightSlider');
          this.currentStep = 0;
        },1500);
        stepper.next();
        break;
      case 2:
        break;
    }
    console.log(this.addForm.value);
  }
}
