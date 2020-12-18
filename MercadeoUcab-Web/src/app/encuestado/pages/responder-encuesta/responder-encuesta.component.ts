import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Opcion } from '@models/opcion';
import { Usuario } from '@models/usuario';
import { MuestraPoblacion } from '@models/muestraPoblacion';
import { RespuestaService } from '@core/services/respuesta/respuesta.service';
import { PreguntaService } from '@core/services/pregunta/pregunta.service';
import { EstudioService } from '@core/services/estudio/estudio.service';
import { surveyEstudio } from '@core/models/surveyEstudio';
import { surveyPregunta } from '@core/models/surveyPregunta';
import { surveySolicitud } from '@core/models/surveySolicitud';
import { surveyEncuesta } from '@core/models/surveyEncuesta';
import { toBackendAnswer } from '@core/models/toBackendAnswer';

//MODELO DE RESPUESTA CONTROLADA
//los valores *_val corresponden al campo alterable SEGUN EL TIPO DE PREGUNTA
//siendo * siempre la primera letra deL tipo de pregunta a responder
interface RespuestaModel {
  tipo: string;
  a_val: string | null;
  s_val: Opcion | null;
  m_val: Opcion[];
  b_val: boolean | null;
  r_val: number | null;
  done: boolean;
}
@Component({
  selector: 'app-responder-encuesta',
  templateUrl: './responder-encuesta.component.html',
  styleUrls: ['./responder-encuesta.component.css'],
})
export class ResponderEncuestaComponent implements OnInit {
  public userLogged: number;
  _Id: number;
  _preguntas: surveyPregunta[] = [];
  _estudio: surveyEstudio = null;
  _respuestas: RespuestaModel[] = [];
  _sampleOption: Opcion = { _id: 0, nombre_opcion: '' };
  opStatus: string;
  toAdd: toBackendAnswer[] = [];
  searchState: string = '';
  surveyReady: boolean = false;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private _respuestaService: RespuestaService,
    private _preguntaService: PreguntaService,
    private _estudioService: EstudioService
  ) {}
  testPais = {
    _id: 1,
    nombre: 'Test pais',
  };
  testEstado = {
    _id: 1,
    nombre: 'Test estado',
    pais: this.testPais,
  };
  testMunicipio = {
    _id: 1,
    nombre: 'Test  municipio',
    estado: this.testEstado,
  };
  testParroquia = {
    _id: 1,
    nombre: 'Test  parrroquia',
    municipio: this.testMunicipio,
    valorSocioEconomico: 8000,
  };
  sampleUsuario: Usuario = {
    _id: Math.floor(Math.random() * (1000 - 1) + 1),
    nombre: Math.random().toString(36).substr(2, 5),
    apellido: Math.random().toString(36).substr(2, 5),
    rol: 'Administrador',
    correo: Math.random().toString(36).substr(2, 5),
    estado: 'Activo',
  };
  samplePoblacion: MuestraPoblacion = {
    _id: 1,
    genero: 'U',
    nivel_economico: 999,
    nivel_academico: 'ABCD',
    rango_edad_inicio: 25,
    rango_edad_fin: 50,
    cantidad_hijos: 0,
    parroquia: this.testParroquia,
  };
  sampleSolicitud: surveySolicitud = {
    _id: 1,
    estado: 'solicitada',
  };
  samplePregunta1: surveyPregunta = {
    _id: 1,
    pregunta: 'preg abierta',
    tipo: 'abierta',
    rango: '',
    opciones: [],
  };
  samplePregunta2: surveyPregunta = {
    _id: 2,
    pregunta: 'preg multiple',
    tipo: 'multiple',
    rango: '',
    opciones: [
      { _id: 1, nombre_opcion: 'test mult 1' },
      { _id: 2, nombre_opcion: 'test mult 2' },
    ],
  };
  samplePregunta3: surveyPregunta = {
    _id: 3,
    pregunta: 'preg simple',
    tipo: 'simple',
    rango: '',
    opciones: [
      { _id: 1, nombre_opcion: 'test simple 1' },
      { _id: 2, nombre_opcion: 'test simple 2' },
    ],
  };
  samplePregunta4: surveyPregunta = {
    _id: 4,
    pregunta: 'preg boolean',
    tipo: 'boolean',
    rango: '',
    opciones: [],
  };
  samplePregunta5: surveyPregunta = {
    _id: 5,
    pregunta: 'preg rango',
    tipo: 'rango',
    rango: '1&100',
    opciones: [],
  };
  sampleEncuesta: surveyEncuesta[] = [
    {
      _id: 2,
      pregunta: this.samplePregunta1,
    },
    {
      _id: 3,
      pregunta: this.samplePregunta2,
    },
    {
      _id: 4,
      pregunta: this.samplePregunta3,
    },
    {
      _id: 5,
      pregunta: this.samplePregunta4,
    },
    {
      _id: 6,
      pregunta: this.samplePregunta5,
    },
  ];
  getPreguntas() {}
  setRespuestas(pregInd, tipoPreg, data) {
    //let _respuesta: Respuesta;
    switch (tipoPreg) {
      case 'abierta':
        this._respuestas[pregInd].a_val = data;
        break;
      case 'simple':
        if (parseInt(data, 10) - 1 === -1) {
          this._respuestas[pregInd].s_val = null;
        } else {
          this._respuestas[pregInd].s_val = this._estudio.encuesta[
            pregInd
          ].pregunta.opciones[parseInt(data, 10) - 1];
        }
      case 'boolean':
        this._respuestas[pregInd].b_val = data;
        break;
      case 'rango':
        if (data !== '') {
          this._respuestas[pregInd].r_val = parseInt(data, 10);
        } else {
          this._respuestas[pregInd].r_val = parseInt(data, 10);
        }
        break;
    }
    //console.log(this._respuestas[pregInd]);
  }
  setMultOption(pregId, op) {
    let ILength: number = this._respuestas[pregId].m_val.length;
    this._respuestas[pregId].m_val = this._respuestas[pregId].m_val.filter(
      (regOp) => regOp._id !== op._id
    );
    if (ILength === this._respuestas[pregId].m_val.length) {
      this._respuestas[pregId].m_val.push(op);
    }
    //console.log(this._respuestas[pregId].m_val);
  }
  checkMultiple(pregInd: number, opInd: number) {
    let res: boolean = false;
    this._respuestas[pregInd].m_val.forEach((opc, ind) => {
      if (opc._id === opInd) {
        res = true;
      }
    });
    //console.log(res,` for ${opInd}`);
    return res;
  }
  //NT: NO FUE POSIBLE EL PASO DEL ESTUDIO A RESPONDER DESDE LA TABLA DE ESTUDIO ASIGNADOS
  //POR LO QUE SE USA UN ID EXTRAIDO DE LA URL EMPLEADA PARA BUSCAR LA INFO DEL ESTUDIO A RESPONDER
  //EN BSD
  getData(estudioId: number) {
    this.searchState = 'P';
    console.log('GETTING DATA FROM: ', estudioId);
    this._estudioService.getEstudio(estudioId).subscribe(
      (response) => {
        console.log(response);
        this._estudio = response.data;
        this.searchState = 'D';
        //console.log(this._estudio);
        this._estudio.encuesta.forEach((preg, ind) => {
          //INSTANCIAR PREPARACION DE Respuesta
          this._respuestas.push({
            tipo: preg.pregunta.tipo,
            a_val: null,
            s_val: null,
            m_val: [],
            b_val: true,
            r_val: null,
            done: false,
          });
        });
      },
      (error) => {
        console.log(error);
        /*this._estudio = {
          _id: 666,
          estado: 'activo',
          tipo: 'WEB',
          encuestas_esperadas: 333,
          solicitud: this.sampleSolicitud,
          muestra_poblacion: this.samplePoblacion,
          analista: this.sampleUsuario,
          encuesta: this.sampleEncuesta,
        };*/
        this._estudio = testRes.data;
        //console.log(this._estudio);
        this._estudio.encuesta.forEach((preg, ind) => {
          //INSTANCIAR PREPARACION DE Respuesta
          this._respuestas.push({
            tipo: preg.pregunta.tipo,
            a_val: null,
            s_val: null,
            m_val: [],
            b_val: true,
            r_val: null,
            done: false,
          });
        });
        this.searchState = 'D';
      }
    );
  }
  ngOnInit(): void {
    //console.log(this.route.snapshot.paramMap.get('id'));
    // this.userLogged = Number(localStorage.getItem('_id'));
    this.userLogged = 1;
    this._Id = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    this.searchState = '';
    this.opStatus="S";
    this.getData(this._Id);
    //this.getData(4);
  }

  onDir(_route: string): void {
    try {
      //console.log(_route);
      this.router.navigate([_route]);
    } catch (e) {
      console.log(e.message);
    }
  }

  checkResp(pregInd, preg) {
    let resp: RespuestaModel = this._respuestas[pregInd];
    let _done = false;
    switch (preg.tipo) {
      case 'abierta':
        if (resp.a_val !== null) {
          _done = true;
        }
        //SEND VALUE
        break;
      case 'simple':
        if (resp.s_val !== null) {
          _done = true;
        }
        //SEND OPCION
        break;
      case 'multiple':
        if (resp.m_val !== []) {
          _done = true;
        }
        //FOR EACH SEND
        break;
      case 'boolean':
        _done = true;
        //SEND
        break;
      case 'rango':
        if (
          resp.r_val !== null &&
          resp.r_val >= parseInt(preg.rango.split('&')[0], 10) &&
          resp.r_val <= parseInt(preg.rango.split('&')[1], 10)
        ) {
          _done = true;
        }
        //SEND
        break;
    }
    this._respuestas[pregInd].done = _done;
  }
  checkSurvey(): boolean {
    let res: boolean = false;
    //console.log('Checking answers...');
    this._estudio.encuesta.forEach((preg, ind) => {
      this.checkResp(ind, preg.pregunta);
    });

    for (let resp of this._respuestas) {
      if (resp.done !== true) {
        res = true;
        break;
      }
    }
    return res;
  }
  /* 
  //VERSION AUXILIAR
  postRespuestas(pregPos:number,tipoPreg:string,resp:any){
    switch(tipoPreg){
      case 'abierta':
        //SEND VALUE
        break;
      case 'simple':
        //SEND OPCION
        break;
      case 'multiple':
        //FOR EACH SEND
        break;
      case 'boolean':
        //SEND
        break;
      case 'rango':
        //SEND
        break;
    }
  }*/

  saveSurvey(data) {
    this.opStatus="P";
    this._respuestaService.saveSurvey(data).subscribe(
      (response: any) => {
        console.log(response);
        this.opStatus="D";
      },
      (error) => {
        console.log(error);
        this.opStatus="E";
      }
    );
  }
  setBackendAnswer(simple, respuesta, encuestaEstudio, usuario, opcion) {
    let singleAnswer = new toBackendAnswer({ _id: 0 }, { _id: 0 });
    singleAnswer.dtoEncuestaEstudio._id = encuestaEstudio;
    singleAnswer.dtousuario._id = usuario;
    if (simple === true) {
      singleAnswer.dtoopcion = opcion;
    } else {
      singleAnswer.respuesta = respuesta;
    }
    this.toAdd.push(singleAnswer);
  }
  //NT: ESTE METODO SOLO SE HABILITA UNA VEZ COMPLETADAS LAS PREGUNTAS EN LA ENCUESTA CON SUS RESPUESTAS
  postRespuestas() {
    //ARRAY DE RESPUESTAS ESPERADAS Y VALIDADAS
    //this._respuestas
    //PREGUNTAS DEL ESTUDIO CON CARACTERISTICAS ESPECIFICADAS
    //this._estudio.preguntas
    //SI SE RECORRE RESPUESTA SE DESCONOCERA EL TIPO DE PREGUNTA, SI SE RECORREN PREGUNTAS Y POR INDICE DE
    //LA PREGUNTA SE BUSCA LA RESPUESTA ASOCIADA (ES EL MISMO) SE PODRA CONOCER EL TIPO Y DETERMINAR EL
    //ENVIO DE LA RESPUESTA DE ESA MANERA:
    console.log(this._respuestas);
    this._estudio.encuesta.forEach((pregunta, index) => {
      //console.log('Encuesta_estudio ' + pregunta._id);
      //this.setBackendAnswer(false, this._respuestas[index])
      let tipoRespuesta = this._respuestas[index].tipo;
      let respuesta = this._respuestas[index];
      switch (tipoRespuesta) {
        case 'abierta':
          this.setBackendAnswer(
            false,
            respuesta.a_val,
            pregunta._id,
            this.userLogged,
            null
          );
          break;
        case 'simple':
          // Falta, obtener id de la opcion
          this.setBackendAnswer(
            true,
            null,
            pregunta._id,
            this.userLogged,
            respuesta.s_val._id
          );
          break;
        case 'multiple':
          // Falta, obtener id de la opcion y llamar la funcion segun tantas opciones posea
          respuesta.m_val.forEach((opcion, index) => {
            //verificar que funcione
            this.setBackendAnswer(
              true,
              null,
              pregunta._id,
              this.userLogged,
              opcion._id
            );
          });
          break;
        case 'boolean':
          this.setBackendAnswer(
            false,
            respuesta.b_val.toString(),
            pregunta._id,
            this.userLogged,
            null
          );
          break;
        case 'rango':
          // posible error por el valor
          this.setBackendAnswer(
            false,
            respuesta.r_val.toString(),
            pregunta._id,
            this.userLogged,
            null
          );
          break;
      }
    });
    console.log(this.toAdd);
    let backendStyle: any = {};
    backendStyle.respuestas = this.toAdd;
    this.saveSurvey(backendStyle);
    this.router.navigate(['']);
  }
}

let testRes = {
  status: 200,
  data: {
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
    ],
  },
};

/* 
  CAMBIOS RELEVANTES
    Se cambiaron los modelos a los survey<Modelo>, ya que el json no contiene 
    todos los modelos iguales a los que se tienen, ejemplo en solicitud ni en 
    pregunta tienen usuario (Json de abajo), se cambie las variables segun los 
    cambios que hice, en eso debe estar el error.

  FORMATO DEL JSON
  {
    "status": 200,
    "data": {
        "_id": 1,
        "estado": "En ejecucion",
        "tipo": "En linea",
        "encuestas_esperadas": 1,
        "solicitud": {
            "_id": 1,
            "estado": "solicitada"
        },
        "analista": {
            "_id": 6,
            "nombre": "Macon",
            "apellido": "Mcleod",
            "correo": "MM10@gmail.com",
            "rol": "administrador"
        },
        "muestra_poblacion": {
            "_id": 1,
            "genero": "masculino",
            "nivel_academico": "Bachiller",
            "rango_edad_inicio": 10,
            "rango_edad_fin": 50,
            "cantidad_hijos": 2,
            "parroquia": {
                "_id": 6,
                "nombre": "Eglise Notre Dame De Rumengol",
                "valor_socioeconomico": 1,
                "municipio": {
                    "_id": 7,
                    "nombre": "Le Faou",
                    "estado": {
                        "_id": 7,
                        "nombre": "Breteña",
                        "pais": {
                            "_id": 4,
                            "nombre": "Francia"
                        }
                    }
                }
            }
        },
        "encuesta": [
            {
                "_id": 1,
                "pregunta": {
                    "_id": 1,
                    "pregunta": "Pregunta 1: Le parecio comodo el mueble? ",
                    "tipo": "abierta"
                }
            },
            {
                "_id": 2,
                "pregunta": {
                    "_id": 2,
                    "pregunta": "Pregunta 2: Recomendaria este mueble a otras personas?",
                    "tipo": "boolean"
                }
            },
            {
                "_id": 3,
                "pregunta": {
                    "_id": 3,
                    "pregunta": "Pregunta 3: El precio del mueble le parece que esta bien justificado?",
                    "tipo": "abierta"
                }
            },
            {
                "_id": 4,
                "pregunta": {
                    "_id": 4,
                    "pregunta": "Pregunta 4: Que problemas encontro en nuestro mueble?",
                    "tipo": "abierta"
                }
            }
        ]
    }
}

*/
