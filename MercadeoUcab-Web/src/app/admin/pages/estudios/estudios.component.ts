import { Component, OnInit, ViewChild } from '@angular/core';
import { Form, FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { DelEstudioDialogComponent } from '../../components/dialogs/del-estudio-dialog/del-estudio-dialog.component';
import { UpdEstudioDialogComponent } from '../../components/dialogs/upd-estudio-dialog/upd-estudio-dialog.component';
import { AddPreguntaDialogComponent } from '../../components/dialogs/add-pregunta-dialog/add-pregunta-dialog.component';
import { Estudio } from '@models/estudio';
import { Marca } from '@models/marca';
import { Pregunta } from '@models/pregunta';
import { Presentacion } from '@models/presentacion';
import { Solicitud } from '@models/solicitud';
import { EstudioService } from '@core/services/estudio/estudio.service';
import { SolicitudService } from '@core/services/solicitud/solicitud.service';
import { UtilService } from '@core/services/utils/util.service';
import { Usuario } from '@core/models/usuario';
import { Muestra_poblacionService } from '@core/services/muestra_poblacion/muestra_poblacion.service';
import { Ocupacion } from '@core/models/ocupacion';
import { Parroquia } from '@core/models/parroquia';
import { ParroquiaService } from '@core/services/parroquia/parroquia.service';
import { Municipio } from '@core/models/municipio';
import { Estado } from '@core/models/estado';
import { Pais } from '@core/models/pais';

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
  suggestLoading:string =""//I,P,D
  preguntas: Pregunta[] = [];
  pregAsoc: any[] = [];

  targetEstudio:any = null;
  targetPoblacion:any = null;

  displayedColumns: string[] = ['id', 'selector', 'ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  poblacionSuggests : any[] = [];
  preguntaSuggests : any[] = [];
  addForm:FormGroup;
  poblacionForm:FormGroup;
  searchForm:FormGroup;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  analistasDisp: any[]=[];
  parroquias: any[]=[];
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
    private _utilsService:UtilService,
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
  @ViewChild('addPreg') private addPregComponent: AddPreguntaDialogComponent;
  async openAddPregModal() {
    return await this.addPregComponent.open();
  }
  ngOnInit(): void {
    this.opStatus = "S";
    this.suggestLoading = "I";
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
      estado:'en ejecución',
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
    this.getAnalistasDisp();
  }
  removePregAsoc(toFilter:number){
    this.pregAsoc = this.pregAsoc.filter(preg => preg._id !== toFilter);
  }
  addPregAsoc(toPush){
    let _push = true;
    this.pregAsoc.forEach((preg,ind) => { 
      if(preg._id === toPush._id){
        _push = false;
      }
    });
    if(_push === true){
      this.pregAsoc.push(toPush);
    }
  }
  checkPregAsoc(checkId:number):boolean{
    let _in = false;
    for(let preg of this.pregAsoc){
      if(preg._id === checkId){ 
        _in = true;
        break;
      }
    }
    return _in;
  }
  addPoblacion(data,stepper){
    if(this.targetPoblacion === null){
    console.log(data);
    this._poblacionService.createMuestraPoblacion(data).subscribe(
      (response)=>{
        console.log(response);
        this.addForm.get('fk_muestra_poblacion').setValue(response['data']._id);
        this.stepCheck(1,stepper);
      },
      (error) => {
        console.log(error);
        this.addForm.get('fk_muestra_poblacion').setValue(Math.floor(Math.random()*(1000-1)+1));
        this.stepCheck(1,stepper);
      }
    );
    }
    else{
      this.addForm.get('fk_muestra_poblacion').setValue(this.targetPoblacion._id);
      this.stepCheck(1,stepper);
    } 
  }
  getSuggestPoblacion(){
    //ESPACIO DE SOLICITUD DE POBLACIONES SUGERIDAS
    this.suggestLoading="P";
    setTimeout(() => {
      this.suggestLoading="D";
      this.poblacionSuggests = [{
      _id:Math.floor(Math.random()*(1000-1)+1),
      genero: 'masculino',
      nivel_academico: 'Bachiller',
      nivel_economico: 3,
      rango_edad_inicio: 10,
      rango_edad_fin: 50,
      cantidad_hijos: 2,
      Fk_ocupacion:{_id:1,nombre:'test Ocupacion'},
      parroquia: {
        _id: 6,
        nombre: 'Eglise Notre Dame De Rumengol',
        valorSocioEconomico: 3,
        nivel_economico: 3,
        municipio: {
          _id: 7,
          nombre: 'Le Faou',
          estado: {
            _id: 7,
            nombre: 'Breteña',
            pais: {
              _id: 4,
              nombre: 'Francia',
            },
          },
        },
      },
    },
    {
     _id:Math.floor(Math.random()*(1000-1)+1),
      genero: 'masculino',
      nivel_academico: 'Bachiller',
      nivel_economico: 3,
      rango_edad_inicio: 10,
      rango_edad_fin: 50,
      cantidad_hijos: 2,
      Fk_ocupacion:{_id:1,nombre:'test Ocupacion'},
      parroquia: {
        _id: 6,
        nombre: 'Eglise Notre Dame De Rumengol',
        valorSocioEconomico: 3,
        nivel_economico: 3,
        municipio: {
          _id: 7,
          nombre: 'Le Faou',
          estado: {
            _id: 7,
            nombre: 'Breteña',
            pais: {
              _id: 4,
              nombre: 'Francia',
            },
          },
        },
      },
    }];

    },2000);
  }
  getSuggestPregunta(){
    //OBTENCION DE PREGUNTAS REGISTRADAS SUGERIDAS
    this.suggestLoading="P";
    setTimeout(() => {
      this.suggestLoading="D";
      this.preguntaSuggests = [
      {
        _id: 1,
        pregunta: {
          _id: 1,
          pregunta: 'Pregunta 1: Le parecio comodo el mueble? ',
          tipo: 'abierta',
        },
      },
      {
        _id: 7,
        pregunta: {
          _id: 2,
          pregunta: 'Pregunta 2: Recomendaria este mueble a otras personas?',
          tipo: 'boolean',
        },
      },
      {
        _id: 3,
        pregunta: {
          _id: 3,
          pregunta:
            'Pregunta 3: El precio del mueble le parece que esta bien justificado?',
          tipo: 'abierta',
          rango: '',
        },
      },
      {
        _id: 24,
        pregunta: {
          _id: 4,
          pregunta: 'Pregunta 4: Que problemas encontro en nuestro mueble?',
          tipo: 'abierta',
        },
      },
      ];
    },2000);
  }
  setPoblacion(data){
    this.targetPoblacion = data;
    if(data !== null){
      console.log(data);
      //SETEAR CAMPOS DE FORMULARIO
      this.poblacionForm.setValue({
        genero: data.genero,
        nivelEconomico:data.nivel_economico,
        nivelAcademico:data.nivel_academico,
        rangoEdadInicio:data.rango_edad_inicio,
        rangoEdadFin:data.rango_edad_fin,
        cantidadHijos:data.cantidad_hijos,
        fk_lugar:data.parroquia._id,
        fk_ocupacion:data.Fk_ocupacion._id,
      });
    }
    else{
      this.poblacionForm.setValue({
        genero:null,
        nivelEconomico:null,
        nivelAcademico:null,
        rangoEdadInicio:null,
        rangoEdadFin:null,
        cantidadHijos:null,
        fk_lugar:null,
        fk_ocupacion:null,
      });
    }
  }
  getAnalistasDisp(){ 
    //OBTENCION DE ANALSITAS REGISTRADOS
    this._utilsService.getUsuariosAnalistas().subscribe(
      (response)=>{
        this.analistasDisp = response['data'];
      },
      (error)=>{
        this.analistasDisp = [
        {
          _id:Math.floor(Math.random()*(1000-1)+1),
          nombre:'Test Analista',
          apellido:Math.random().toString(36).substr(2, 5),
          rol:'Analista',
          correo:Math.random().toString(36).substr(2, 5),
          estado:'Activo',
        }
        ];
      }
    );
  }
  getParroquias(){
    //OBTENCION DE PARROQUIAS REGISTRADAS
    this._parroquiaService.getParroquias().subscribe(
      (response)=>{
        this.parroquias = response.data;
      },
      (error) => {
        this.parroquias =[this.testParroquia,{
        _id: 6,
        nombre: 'Eglise Notre Dame De Rumengol',
        valorSocioEconomico: 3,
        nivel_economico: 3,
        municipio: {
          _id: 7,
          nombre: 'Le Faou',
          estado: {
            _id: 7,
            nombre: 'Breteña',
            pais: {
              _id: 4,
              nombre: 'Francia',
            },
          },
        },
      },
      ];
      }
    );
  }
  getOcupaciones(){
    //OBTENCION DE OCUPACIONES REGISTRADAS
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
  /*
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
  }*/

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
    this.opStatus = "P";
    console.log(this.addForm.value);
    this._estudioService.createEstudio(this.addForm.value).subscribe(
      (response) => {
        console.log(response);
        this.opStatus="D";
      },
      (error) => {
        console.log(error);
        this.opStatus="E";
      }
    );
  }

  invokeSearch() {
    this.estudios = [];
    this.userSelection = 0;
    let toAdd: any = {...this.searchForm.value};
    this.getEstudios();
    this.searchState = 'P';
  }
  setOperation(chOp: string) {
    this.op = chOp;
    if (chOp !== '') {
      this.searchState = 'I';
      this.opStatus="S";
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
        document.getElementById('addStepper').classList.remove('initLeft');
        this.getSuggestPoblacion();
        this.currentStep = 1; 
        stepper.next();
        break;
      case 1:
        //document.getElementById('addStepper').classList.add('rightSlider');
        //document.getElementById('suggests').classList.add('SlideOut');
        /*setTimeout(() => {
          //this.currentStep = 0;
          document.getElementById('addStepper').classList.add('initleft');
          document.getElementById('addStepper').classList.remove('rightSlider');
        },1500);*/
        this.getSuggestPregunta();
        this.currentStep = 2; 
        stepper.next();
        break;
      case 2:
        //this.invokeService();
        this.addForm.get('preguntas').setValue(this.pregAsoc.map((p,ind) => {
          return {  _id:p._id }
        }));
        document.getElementById('addStepper').classList.add('rightSlider');
        document.getElementById('suggests').classList.add('SlideOut');
        this.currentStep = 3; 
        stepper.next();
        break;
      case 3:
        this.invokeService();
        break;
    }
    console.log(this.addForm.value);
  }
}
