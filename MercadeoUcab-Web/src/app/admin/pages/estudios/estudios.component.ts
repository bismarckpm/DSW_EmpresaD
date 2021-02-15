import { Component, OnInit, ViewChild } from '@angular/core';
import {
  Form,
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { DelEstudioDialogComponent } from '../../components/dialogs/del-estudio-dialog/del-estudio-dialog.component';
import { UpdEstudioDialogComponent } from '../../components/dialogs/upd-estudio-dialog/upd-estudio-dialog.component';
import { AddPreguntaDialogComponent } from '../../components/dialogs/add-pregunta-dialog/add-pregunta-dialog.component';
import { BasicInfoDialogComponent } from '../../components/dialogs/basic-info-dialog/basic-info-dialog.component';
import { Estudio } from '@models/estudio';
import { Marca } from '@models/marca';
import { Pregunta } from '@models/pregunta';
//import { Presentacion } from '@models/presentacion';
import { Solicitud } from '@models/solicitud';
import { EstudioService } from '@core/services/estudio/estudio.service';
import { SolicitudService } from '@core/services/solicitud/solicitud.service';
import { UtilService } from '@core/services/utils/util.service';
import { Usuario } from '@core/models/usuario';
import { Muestra_poblacionService } from '@core/services/muestra_poblacion/muestra_poblacion.service';
import { Ocupacion } from '@core/models/ocupacion';
import { Parroquia } from '@core/models/parroquia';
import { ParroquiaService } from '@core/services/parroquia/parroquia.service';
import { OcupacionService } from '@core/services/ocupacion/ocupacion.service';
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
  animations: [],
})
export class EstudiosComponent implements OnInit {
  op: string;
  searchState: string; //U,I,P,D
  solicitudSelec: number;
  opStatus: string;
  currentStep: number = 0;
  solicitudes: Solicitud[] = [];
  ocupaciones: Ocupacion[] = [];
  estudios: any[] = [];
  dataSource: MatTableDataSource<any>;
  userSelection: number = 0;
  suggestLoading: string = ''; //I,P,D
  preguntas: Pregunta[] = [];
  pregAsoc: any[] = [];
  minAge: number = 0;
  maxAge: number = 0;
  targetEstudio: any = null;
  targetPoblacion: any = null;
  targetSolicitud: any = null;
  displayedColumns: string[] = ['estado', 'asignado', 'selector', 'ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  poblacionSuggests: any[] = [];
  preguntaSuggests: any[] = [];
  addForm: FormGroup;
  poblacionForm: FormGroup;
  searchForm: FormGroup;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  analistasDisp: any[] = [];
  parroquias: any[] = [];
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
    municipio: this.testMunicipio,
    valorSocioEconomico: 8000,
  };
  constructor(
    private formBuilder: FormBuilder,
    private _estudioService: EstudioService,
    private _solicitudService: SolicitudService,
    private _poblacionService: Muestra_poblacionService,
    private _parroquiaService: ParroquiaService,
    private _utilsService: UtilService,
    private _ocupacionService: OcupacionService
  ) {}
  testUser: Usuario = {
    _id: Math.floor(Math.random() * (1000 - 1) + 1),
    nombre: Math.random().toString(36).substr(2, 5),
    apellido: Math.random().toString(36).substr(2, 5),
    rol: 'Administrador',
    correo: Math.random().toString(36).substr(2, 5),
    estado: 'Activo',
  };
  @ViewChild('updEstudio') private updComponent: UpdEstudioDialogComponent;
  async openUpdModal() {
    return await this.updComponent.open();
  }
  @ViewChild('delEstudio') private delComponent: DelEstudioDialogComponent;
  async openDelModal() {
    return await this.delComponent.open();
  }
  @ViewChild('infoSol') private infoSolComponent: BasicInfoDialogComponent;
  async openSolModal() {
    return await this.infoSolComponent.open();
  }
  @ViewChild('addPreg') private addPregComponent: AddPreguntaDialogComponent;
  async openAddPregModal() {
    return await this.addPregComponent.open();
  }
  @ViewChild('info') private infoComponent: BasicInfoDialogComponent;
  async openInfoModal() {
    return await this.infoComponent.open();
  }
  ngOnInit(): void {
    this.opStatus = 'S';
    this.suggestLoading = 'I';
    this.searchForm = this.formBuilder.group({
      /**/
      estado: null,
      tipo: null,
      analista: null,
    });
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
      estado: 'en ejecución',
      tipo: null,
      encuestasEsperadas: null,
      fk_usuario: null,
      //fk_muestra_poblacion: null,
      solicitud: null,
      preguntas: [],
    });
    this.poblacionForm = this.formBuilder.group({
      genero: null,
      nivelEconomico: null,
      nivelAcademico: null,
      rangoEdadInicio: null,
      rangoEdadFin: null,
      cantidadHijos: null,
      fk_lugar: null,
      fk_ocupacion: null,
    });
    this.getOcupaciones();
    this.getParroquias();
    this.setOperation('');
    this.getAnalistasDisp();
  }
  getYearDiff(t: string): number {
    let _t = new Date(t);
    let _n: Date = new Date(Date.now());
    return _n.getFullYear() - _t.getFullYear();
  }
  ageEdit(min, max) {
    let _auxDate: Date = new Date(Date.now());

    if (min !== null) {
      //console.log('Edad minima ',min);
      this.minAge = min;
      let newMin: Date = new Date(
        _auxDate.getFullYear() - min,
        _auxDate.getMonth(),
        _auxDate.getDay()
      );
      console.log(newMin);
      this.poblacionForm.get('rangoEdadInicio').setValue(newMin);
    } else {
      //console.log('Edad máxima ',max);
      this.maxAge = max;
      let newMax: Date = new Date(
        _auxDate.getFullYear() - max,
        _auxDate.getMonth(),
        _auxDate.getDay()
      );
      console.log(newMax);
      this.poblacionForm.get('rangoEdadFin').setValue(newMax);
    }
  }

  addCreatedPreg(newPreg: any) {
    this.preguntaSuggests.push(newPreg);
    this.pregAsoc.push(newPreg.pregunta);
    //console.log(newPreg);
  }
  removePregAsoc(toFilter: number) {
    this.pregAsoc = this.pregAsoc.filter((preg) => preg._id !== toFilter);
  }
  addPregAsoc(toPush) {
    let _push = true;
    this.pregAsoc.forEach((preg, ind) => {
      if (preg._id === toPush._id) {
        _push = false;
      }
    });
    if (_push === true) {
      this.pregAsoc.push(toPush);
    }
  }
  checkPregAsoc(checkId: number): boolean {
    let _in = false;
    for (let preg of this.pregAsoc) {
      if (preg._id === checkId) {
        _in = true;
        break;
      }
    }
    return _in;
  }
  addPoblacion(data, stepper) {
    if (this.targetPoblacion === null) {
      console.log(data);
      this._poblacionService.createMuestraPoblacion(data).subscribe(
        (response: any) => {
          console.log(response);
          this.addForm.get('fk_muestra_poblacion').setValue(response._id);
          this.stepCheck(1, stepper);
        },
        (error) => {
          console.log(error);
          this.addForm
            .get('fk_muestra_poblacion')
            .setValue(Math.floor(Math.random() * (1000 - 1) + 1));
          this.stepCheck(1, stepper);
        }
      );
    } else {
      this.addForm
        .get('fk_muestra_poblacion')
        .setValue(this.targetPoblacion._id);
      this.stepCheck(1, stepper);
    }
  }
  getSuggestPoblacion() {
    //ESPACIO DE SOLICITUD DE POBLACIONES SUGERIDAS
    this.suggestLoading = 'P';
    this._utilsService
      .getPoblacionRecomendadasOfSolicitud(this.addForm.value.solicitud)
      .subscribe(
        (response: any) => {
          console.log(response.data);
          this.poblacionSuggests = response.data;
          this.suggestLoading = 'D';
        },
        (error) => {
          console.log(<any>error);
          this.poblacionSuggests = [
            {
              _id: 1,
              genero: 'masculino',
              nivel_academico: 'Bachiller',
              nivel_economico: 'medio',
              rango_edad_inicio: new Date('1/2/2011'),
              rango_edad_fin: new Date('1/2/1971'),
              cantidad_hijos: 2,
              ocupacion: { _id: 1, nombre: 'test Ocupacion' },
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
            /*{
              _id: 5,
              genero: 'masculino',
              nivel_academico: 'Bachiller',
              nivel_economico: 3,
              rango_edad_inicio: 10,
              rango_edad_fin: 50,
              cantidad_hijos: 2,
              ocupacion: { _id: 1, nombre: 'test Ocupacion' },
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
            },*/
          ];
          this.suggestLoading = 'D';
        }
      );
  }
  getPreguntasSugeridas() {
    this.suggestLoading = 'P';
    this._utilsService
      .getPreguntasRecomendadasOfSolicitud(this.addForm.value.solicitud)
      .subscribe(
        (response: any) => {
          if (response.status != 204) {
            console.log(response);
            this.preguntaSuggests = [...response.data];
            this.suggestLoading = 'D';
          } else {
            // Caso de que no existan preguntas sugeridas
          }
        },
        (error) => {
          console.log(<any>error);
          this.preguntaSuggests = [
            {
              _id: 1,
              pregunta: {
                _id: 1,
                nombre: 'Pregunta 1: Le parecio comodo el mueble? ',
                tipo: 'abierta',
              },
            },
            {
              _id: 7,
              pregunta: {
                _id: 2,
                nombre:
                  'Pregunta 2: Recomendaria este mueble a otras personas?',
                tipo: 'boolean',
              },
            },
            {
              _id: 3,
              pregunta: {
                _id: 3,
                nombre:
                  'Pregunta 3: El precio del mueble le parece que esta bien justificado?',
                tipo: 'abierta',
                rango: '',
              },
            },
            {
              _id: 24,
              pregunta: {
                _id: 4,
                nombre: 'Pregunta 4: Que problemas encontro en nuestro mueble?',
                tipo: 'abierta',
              },
            },
          ];
          this.suggestLoading = 'D';
        }
      );
  }
  /*
  getSuggestPregunta() {
    //OBTENCION DE PREGUNTAS REGISTRADAS SUGERIDAS
    this.suggestLoading = 'P';
    setTimeout(() => {
      this.suggestLoading = 'D';
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
    }, 2000);
  }*/
  setPoblacion(data) {
    this.targetPoblacion = data;
    if (data !== null) {
      console.log(data);
      //SETEAR CAMPOS DE FORMULARIO
      let _auxDate: Date = new Date(Date.now());

      let _Min: Date = new Date(data.rango_edad_inicio);
      let _Max: Date = new Date(data.rango_edad_fin);

      this.minAge = _auxDate.getFullYear() - _Min.getFullYear();
      this.maxAge = _auxDate.getFullYear() - _Max.getFullYear();
      this.poblacionForm.setValue({
        genero: data.genero,
        nivelEconomico: data.nivel_economico,
        nivelAcademico: data.nivel_academico,
        rangoEdadInicio: data.rango_edad_inicio,
        rangoEdadFin: data.rango_edad_fin,
        cantidadHijos: data.cantidad_hijos,
        fk_lugar: data.parroquia._id,
        fk_ocupacion: data.ocupacion._id,
      });
    } else {
      this.poblacionForm.setValue({
        genero: null,
        nivelEconomico: null,
        nivelAcademico: null,
        rangoEdadInicio: null,
        rangoEdadFin: null,
        cantidadHijos: null,
        fk_lugar: null,
        fk_ocupacion: null,
      });
    }
  }
  getAnalistasDisp() {
    //OBTENCION DE ANALSITAS REGISTRADOS
    this._utilsService.getUsuariosAnalistas().subscribe(
      (response) => {
        this.analistasDisp = response['data'];
      },
      (error) => {
        this.analistasDisp = [
          {
            _id: Math.floor(Math.random() * (1000 - 1) + 1),
            nombre: 'Test Analista',
            apellido: Math.random().toString(36).substr(2, 5),
            rol: 'Analista',
            correo: Math.random().toString(36).substr(2, 5),
            estado: 'Activo',
          },
        ];
      }
    );
  }
  getParroquias() {
    //OBTENCION DE PARROQUIAS REGISTRADAS
    this._parroquiaService.getParroquias().subscribe(
      (response) => {
        this.parroquias = response.data;
      },
      (error) => {
        this.parroquias = [
          this.testParroquia,
          {
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
  getOcupaciones() {
    //OBTENCION DE OCUPACIONES REGISTRADAS
    this._ocupacionService.getOcupaciones().subscribe(
      (response) => {
        this.ocupaciones = response.data;
      },
      (error) => {
        console.log(<any>error);
        this.ocupaciones = [{ _id: 1, nombre: 'Ocupacion Test' }];
      }
    );
  }
  getEstudios() {
    this._estudioService.getEstudios().subscribe(
      (response) => {
        console.log(response);
        this.estudios = response.data;
        this.dataSource = new MatTableDataSource<any>(this.estudios);
        this.searchState = 'D';
      },
      (error) => {
        console.log(error);
        this.estudios = [
          {
            _id: 1,
            estado: 'En ejecucion',
            tipo: 'En linea',
            encuestas_esperadas: 1,
            solicitud: {
              _id: 1,
              estado: 'solicitada',
            },
            analista: {
              _id: 6,
              nombre: 'Macon',
              apellido: 'Mcleod',
              correo: 'MM10@gmail.com',
              rol: 'administrador',
              estado: 'test',
            },
            muestra_poblacion: {
              _id: 1,
              genero: 'masculino',
              nivel_academico: 'Bachiller',
              nivel_economico: 3,
              rango_edad_inicio: 10,
              rango_edad_fin: 50,
              cantidad_hijos: 2,
              Fk_ocupacion: { _id: 1, nombre: 'test Ocupacion' },
              parroquia: {
                _id: 6,
                nombre: 'Eglise Notre Dame De Rumengol',
                // "valor_socioeconomico": 1,
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
            encuesta: [
              {
                _id: 1,
                pregunta: {
                  _id: 1,
                  nombre: 'Pregunta 1: Le parecio comodo el mueble? ',
                  tipo: 'abierta',
                },
              },
              {
                _id: 7,
                pregunta: {
                  _id: 2,
                  nombre:
                    'Pregunta 2: Recomendaria este mueble a otras personas?',
                  tipo: 'boolean',
                },
              },
              {
                _id: 3,
                pregunta: {
                  _id: 3,
                  nombre:
                    'Pregunta 3: El precio del mueble le parece que esta bien justificado?',
                  tipo: 'abierta',
                  rango: '',
                },
              },
              {
                _id: 24,
                pregunta: {
                  _id: 4,
                  nombre:
                    'Pregunta 4: Que problemas encontro en nuestro mueble?',
                  tipo: 'abierta',
                },
              },
            ],
          },
          {
            _id: 3,
            estado: 'En ejecucion',
            tipo: 'En linea',
            encuestas_esperadas: 1,
            solicitud: {
              _id: 1,
              estado: 'solicitada',
            },
            analista: {
              _id: 6,
              nombre: 'Macon',
              apellido: 'Mcleod',
              correo: 'MM10@gmail.com',
              rol: 'administrador',
              estado: 'test',
            },
            muestra_poblacion: {
              _id: 1,
              genero: 'masculino',
              nivel_academico: 'Bachiller',
              nivel_economico: 3,
              rango_edad_inicio: 10,
              rango_edad_fin: 50,
              cantidad_hijos: 2,
              Fk_ocupacion: { _id: 1, nombre: 'test Ocupacion' },
              parroquia: {
                _id: 6,
                nombre: 'Eglise Notre Dame De Rumengol',
                // "valor_socioeconomico": 1,
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
            encuesta: [
              {
                _id: 1,
                pregunta: {
                  _id: 1,
                  nombre: 'Pregunta 1: Le parecio comodo el mueble? ',
                  tipo: 'abierta',
                },
              },
              {
                _id: 7,
                pregunta: {
                  _id: 2,
                  nombre:
                    'Pregunta 2: Recomendaria este mueble a otras personas?',
                  tipo: 'boolean',
                },
              },
              {
                _id: 3,
                pregunta: {
                  _id: 3,
                  nombre:
                    'Pregunta 3: El precio del mueble le parece que esta bien justificado?',
                  tipo: 'abierta',
                  rango: '',
                },
              },
              {
                _id: 24,
                pregunta: {
                  _id: 4,
                  nombre:
                    'Pregunta 4: Que problemas encontro en nuestro mueble?',
                  tipo: 'abierta',
                },
              },
            ],
          },
        ];
        this.dataSource = new MatTableDataSource<any>(this.estudios);
        this.searchState = 'D';
      }
    );
  }

  addEstudio(data) {
    this._estudioService.createEstudio(data).subscribe(
      (response) => {
        console.log(response);
        //alert('Se agrego el estudio correctamente');
        this.opStatus = 'D';
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
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
    this._solicitudService.getSolicitudByEstado('solicitada').subscribe(
      (response: any) => {
        console.log(response);
        this.solicitudes = response.data;
      },
      (error) => {
        console.log(error);
        this.solicitudes = [
          {
            _id: 1,
            estado: 'solicitada',
            usuario: {
              _id: 31,
              nombre: 'Caesar',
              apellido: 'Mosley',
              rol: 'cliente',
              estado: 'activo',
              correo: 'CM10@gmail.com',
            },
            //"marca": "Sin especificar",
            comentarios: 'Sin comentarios',
            presentaciones: [
              {
                _id: 1,
                tipo: 'Madera',
                Cantidad: '30x50',
                fk_tipo: {
                  _id: 1,
                  nombre: 'Camas',
                  subCategoria: {
                    _id: 1,
                    nombre: 'Dormitorios',
                    categoria: { _id: 1, nombre: 'Muebles' },
                  },
                },
              },
            ],
            muestraPoblacion: {
              _id: 1,
              genero: 'masculino',
              Fk_ocupacion: { _id: 1, nombre: 'test Ocupacion' },
              nivel_economico: 'Alto',
              nivel_academico: 'Licenciado',
              rango_edad_inicio: '1940-01-01',
              rango_edad_fin: '2015-01-01',
              cantidad_hijos: 1,
              parroquia: {
                _id: 1,
                nombre: 'San Camilo',
                valorSocioEconomico: 1,
                municipio: {
                  _id: 1,
                  nombre: 'Manaos',
                  estado: {
                    _id: 1,
                    nombre: 'Amazonas',
                    pais: { _id: 1, nombre: 'Venezuela' },
                  },
                },
              },
            },
          },
        ];
        /*this.solicitudes = [
          {
            _id: 13,
            estado: 'activo',
            usuario: this.testUser,
            marca: { _id: 1, nombre: 'TEST MARCA' },
            tipos: [{ _id: 1, nombre: 'test Tipo' }],
            presentaciones: [{ _id: 1, tipo: 'volumen'}],
            subcategorias: [
              {
                _id: 1,
                nombre: 'test SubCategoria',
                categoria: { _id: 1, nombre: 'test Categoria' },
              },
            ],
          },
          {
            _id: 34,
            estado: 'activo',
            usuario: this.testUser,
            marca: { _id: 1, nombre: 'TEST MARCA' },
            tipos: [{ _id: 1, nombre: 'test Tipo' }],
            presentaciones: [{ _id: 1, tipo: 'volumen', cantidad: '800ml' }],
            subcategorias: [
              {
                _id: 1,
                nombre: 'test SubCategoria',
                categoria: { _id: 1, nombre: 'test Categoria' },
              },
            ],
          },
          {
            _id: 107,
            estado: 'activo',
            usuario: this.testUser,
            marca: { _id: 1, nombre: 'TEST MARCA' },
            tipos: [{ _id: 1, nombre: 'test Tipo' }],
            presentaciones: [{ _id: 1, tipo: 'volumen', cantidad: '800ml' }],
            subcategorias: [
              {
                _id: 1,
                nombre: 'test SubCategoria',
                categoria: { _id: 1, nombre: 'test Categoria' },
              },
            ],
          },
        ];*/
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
    this.opStatus = 'P';
    let toAdd: any = { ...this.addForm.value };
    console.log(toAdd);
    this._estudioService.createEstudio(this.addForm.value).subscribe(
      (response) => {
        console.log(response);
        this.opStatus = 'D';
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
      }
    );
  }

  invokeSearch() {
    this.estudios = [];
    this.userSelection = 0;
    this.getEstudios();
    this.searchState = 'P';
  }
  setOperation(chOp: string) {
    this.op = chOp;
    if (chOp !== '') {
      this.searchState = 'I';
      this.opStatus = 'S';
    } else {
      this.searchState = 'U';
    }
  }
  //CONTROL DE SELECCIÓN EN TABLA DE DATOS
  selectUser(id: number, data: any) {
    if (id === this.userSelection) {
      this.userSelection = 0;
      this.targetEstudio = null;
    } else {
      this.userSelection = id;
      this.targetEstudio = data;
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
  stepCheck(ind, stepper) {
    switch (ind) {
      case 0:
        let auxFkPob = this.solicitudes.filter(
          (s, ind) => s._id === this.addForm.get('solicitud').value
        );
        this.targetSolicitud = auxFkPob;
        //this.addForm.get('fk_muestra_poblacion').setValue(auxFkPob[0].muestraPoblacion._id);
        document.getElementById('addStepper').classList.add('leftSlider');
        document.getElementById('addStepper').classList.remove('initLeft');
        this.getPreguntasSugeridas();
        this.currentStep = 2;
        stepper.next();
        break;
      /*
      case 1:
        this.getPreguntasSugeridas();
        this.currentStep = 2;
        stepper.next();
        break; */
      case 2:
        this.addForm.get('preguntas').setValue(
          this.pregAsoc.map((p, ind) => {
            return { _id: p._id };
          })
        );
        document.getElementById('addStepper').classList.remove('leftSlider');
        document.getElementById('addStepper').classList.add('rightSlider');
        document.getElementById('suggests').classList.add('SlideOut');
        setTimeout(() => {
          document.getElementById('addStepper').classList.remove('rightSlider');
          document.getElementById('addStepper').classList.add('initLeft');
          this.currentStep = 3;
        }, 2000);
        stepper.next();
        break;
      case 3:
        this.invokeService();
        break;
    }
    //console.log(this.addForm.value);
  }
}
