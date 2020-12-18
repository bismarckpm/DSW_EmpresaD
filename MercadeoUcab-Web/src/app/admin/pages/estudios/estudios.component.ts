import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
})
export class EstudiosComponent implements OnInit {
  op: string;
  searchState: string; //U,I,P,D
  solicitudSelec: number;
  opStatus: string;
  solicitudes: Solicitud[] = [
    /*{ _id:1, estado:'I', activo:false, creado_el:new Date(), modificado_el:new Date()},
    { _id:2, estado:'I', activo:false, creado_el:new Date(), modificado_el:new Date()},
    { _id:3, estado:'A', activo:false, creado_el:new Date(), modificado_el:new Date()},
    { _id:4, estado:'I', activo:false, creado_el:new Date(), modificado_el:new Date()},
    { _id:5, estado:'I', activo:false, creado_el:new Date(), modificado_el:new Date()},
    { _id:6, estado:'I', activo:false, creado_el:new Date(), modificado_el:new Date()}*/
  ];
  estudios: Estudio[] = [
    /*
    {_id:1,estado:'A',tipo:'A',encuestas_esperadas:98},
    {_id:2,estado:'A',tipo:'A',encuestas_esperadas:3},
    {_id:3,estado:'A',tipo:'A',encuestas_esperadas:16},
    {_id:4,estado:'A',tipo:'A',encuestas_esperadas:50},*/
  ];
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
  searchForm:FormGroup;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _estudioService: EstudioService,
    private _solicitudService: SolicitudService
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
      solicitud:null,
      fk_usuario:null,
      fk_muestra_poblacion:null,
      fk_solicitud: null,
      preguntas:[],
    });
    this.setOperation('');
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
        }];
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
  stepCheck() {
    console.log(this.addForm.value);
  }
}
