import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Opcion } from '@models/opcion';
import { Usuario } from '@models/usuario';
import { MuestraPoblacion } from '@models/muestraPoblacion';
import { RespuestaService } from '@core/services/respuesta/respuesta.service';
import { PreguntaService } from '@core/services/pregunta/pregunta.service';
import { EstudioService } from '@core/services/estudio/estudio.service';
import { toBackendAnswer } from '@core/models/toBackendAnswer';
import { Pregunta } from '@core/models/pregunta';
import { Estudio } from '@core/models/estudio';
import { Solicitud } from '@core/models/solicitud';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';

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
  providers: [
    {
      provide: STEPPER_GLOBAL_OPTIONS,
      useValue: { displayDefaultIndicatorType: false },
    },
  ],
})
export class ResponderEncuestaComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private _respuestaService: RespuestaService,
    private _preguntaService: PreguntaService,
    private _estudioService: EstudioService
  ) {}

  public userLogged: number;
  _Id: number;
  _preguntas: any[] = [];
  _respuestas: RespuestaModel[] = [];
  _sampleOption: Opcion = { _id: 0, nombre: '' };
  opStatus: string;
  toAdd: toBackendAnswer[] = [];
  searchState: string = '';
  surveyReady: boolean = false;
  _usuario: any =  JSON.parse(localStorage.getItem('user_data'));
  alteredEstudio: any = null;
  openAnswer:string = '';
  multiOption:any = [];
  singleOption:any = {};
  boolOption: boolean;
  rangeOption:any = '';
  _encuesta: any = null;
  _answers:any = {};

  testRes = {
  status: 200,
  data: {"_id":1,"estado":"En ejecucion",
        "tipo":"En linea","encuestas_esperadas":1,
        "solicitud":{"_id":1,"estado":"solicitada","usuario":{"_id":31,"nombre":"Caesar","apellido":"Mosley","rol":"cliente","estado":"activo","correo":"CM10@gmail.com"},"marca":"Sin especificar","comentarios":"Sin comentarios",
        "presentaciones":[{"_id":1,"tipo":"Madera","Cantidad":"30x50","fk_tipo":{"_id":1,"nombre":"Camas","subCategoria":{"_id":1,"nombre":"Dormitorios","categoria":{"_id":1,"nombre":"Muebles"}}}}],
        "muestraPoblacion":{"_id":1,"genero":"masculino","nivel_economico":"Alto","nivel_academico":"Licenciado","rango_edad_inicio":"1940-01-01","rango_edad_fin":"2015-01-01",
        "cantidad_hijos":1,"parroquia":{"_id":1,"nombre":"San Camilo","valorSocioEconomico":1,"municipio":{"_id":1,"nombre":"Manaos",
        "estado":{"_id":1,"nombre":"Amazonas","pais":{"_id":1,"nombre":"Venezuela"}}}}}},
        "analista":{"_id":36,"nombre":"Harper","apellido":"Vance",
        "rol":"analista","estado":"activo","correo":"Harper20@gmail.com"},
        "encuesta":[
        {"_id":1,"pregunta":{"_id":1,"nombre":"Que opina del producto? ","tipo":"abierta","usuario":{"_id":26,"nombre":"Macon","apellido":"Mcleod","rol":"administrador","estado":"activo","correo":"MM10@gmail.com"}},
        "respuestas":[
        {
                "respuesta":"respuesta",
                "dtoEncuestaEstudio": 
                    {
                    "_id":22
                    },
                "dtousuario": 
                    {
                    "_id":2
                    }
              },
        ]},
        {"_id":2,"pregunta":{"_id":2,"nombre":"Cuentenos, tuvo algun problema con el producto?","tipo":"abierta","usuario":{"_id":26,"nombre":"Macon","apellido":"Mcleod","rol":"administrador","estado":"activo","correo":"MM10@gmail.com"}},"respuestas":[]},
        {"_id":3,"pregunta":{"_id":3,"nombre":"Como fue su experiencia con el producto?","tipo":"abierta","usuario":{"_id":26,"nombre":"Macon","apellido":"Mcleod","rol":"administrador","estado":"activo","correo":"MM10@gmail.com"}},"respuestas":[]}]},
  };
  doneSurvey(){
    if(Object.keys(this._answers).length !== 0){
      let _done = true;
      Object.entries(this._answers).forEach(([key,value],ind)=> {
        if(this._answers[key].length === 0){
         _done = false;
        }
      });
      return _done;
    }
    return false;
  }
  clearFields(){
    this.openAnswer = '';
    this.multiOption = [];
    this.singleOption = {};
    this.boolOption= false;
    this.rangeOption = '';
  } 

prepAnswer(content,_type){
  console.log(content);
  switch(_type){
    case 'simple':
      break;
    case 'multiple':
      break;
    case 'boolean':
      this.boolOption = content;
      break;  
    case 'abierta':
      this.openAnswer = content;
      break;
    case 'rango':
      this.rangeOption = content;
      break;
   }
}
setSimpleOption(pregInd,opInd){
  let _op = parseInt(opInd,10);
  if(opInd - 1 !== -1){
    this.singleOption = this._encuesta.encuesta[pregInd].opciones[opInd];
    console.log(this.singleOption);
  }
  else {
    this.singleOption = null;
  }
} 

setMultOption(pregId, op) {
  let ILength: number = this.multiOption.length;
  this.multiOption = this.multiOption.filter(
    (regOp) => regOp._id !== op._id
  );
  if (ILength === this.multiOption.length) {
    this.multiOption.push(op);
  }
  console.log(this.multiOption);
}
checkMultiple(pregInd: number, opInd: number) {
  let res: boolean = false;
  this.multiOption.forEach((opc, ind) => {
    if (opc._id === opInd) {
      res = true;
    }
  });
  return res;
}
 postAnswer(user,_type,stepper,_pregId){
  let Answer : toBackendAnswer;
  Answer = new toBackendAnswer({ _id: _pregId }, { _id: this.userLogged });
  Answer.dtoopcion = null;
  switch(_type){
    case 'simple':
      Answer.dtoopcion._id = this.singleOption._id;
      Answer.respuesta=null;
      this.sendAnswer(user,_type,[Answer],_pregId);
      break;
    case 'multiple':
      Answer.respuesta=null;
      let multiAnswers: toBackendAnswer[] = [];
      const userId = this.userLogged;
      this.multiOption.forEach((op,ind) =>{
        let nAnswer: toBackendAnswer = new toBackendAnswer({ _id: _pregId }, { _id: userId });
        Answer.respuesta=null;
        Answer.dtoopcion={_id:op._id};
        multiAnswers.push(nAnswer);
      });
      this.sendAnswer(user,_type,[...multiAnswers],_pregId);
      break;
    case 'boolean':
      //extraer
      Answer.respuesta = this.boolOption.toString();
      this.sendAnswer(user,_type,[Answer],_pregId);
      break;  
    case 'abierta':
      Answer.respuesta = this.openAnswer;
      this.sendAnswer(user,_type,[Answer],_pregId);
      break;
    case 'rango':
      Answer.respuesta = this.rangeOption.toString();
      this.sendAnswer(user,_type,[Answer],_pregId);
      break;
   }
   this.clearFields();
   stepper.next();
 }
 /*setBackendAnswer(simple, respuesta, encuestaEstudio, usuario, opcion) {
  let singleAnswer = new toBackendAnswer({ _id: this._encuesta._id }, { _id: this._usuario._id });
  //singleAnswer.dtoEncuestaEstudio._id = encuestaEstudio;
  //singleAnswer.dtousuario._id = usuario;
  if (simple === true) {
    singleAnswer.dtoopcion = opcion;
  } else {
    singleAnswer.respuesta = respuesta;
  }
  //this.toAdd.push(singleAnswer);
}*/
saveSurvey(user,data,_pregId) {
  //this.opStatus = 'P';
  this._respuestaService.saveSurvey({respuestas:data}).subscribe(
    (response: any) => {
      console.log(response);
      /*this.alterAnswers.emit({user,data,_pregId});
      this._answers[`${_pregId}`].push(data);*/
        for(const ans of data){
         this._answers[`${_pregId}`].push(ans); 
        }
    },
    (error) => {
      console.log(error);
      /*this.alterAnswers.emit({user,data,_pregId});
      this._answers[`${_pregId}`].push(data);*/
      for(const ans of data){
         this._answers[`${_pregId}`].push(ans); 
      }
      
    }
  );
}
 sendAnswer(user,preg,resp,_pregId){
  console.log(resp);
   switch(preg){
    case 'simple':
      this.saveSurvey(user,resp,_pregId);
      break;
    case 'multiple':
      this.saveSurvey(user,resp,_pregId);
      break;
     default:
      this.saveSurvey(user,resp,_pregId);
      break;  
   }
 }
  /*setRespuestas(pregInd, tipoPreg, data) {
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
  }*/
  /*setMultOption(pregId, op) {
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
  }*/
  //NT: NO FUE POSIBLE EL PASO DEL ESTUDIO A RESPONDER DESDE LA TABLA DE ESTUDIO ASIGNADOS
  //POR LO QUE SE USA UN ID EXTRAIDO DE LA URL EMPLEADA PARA BUSCAR LA INFO DEL ESTUDIO A RESPONDER
  //EN BSD
  getData(estudioId: number) {
    this.searchState = 'P';
    console.log('GETTING DATA FROM: ', estudioId);
    this._estudioService.getEstudio(estudioId).subscribe(
      (response) => {
        console.log(response);
        this._encuesta = response.data;
        this._encuesta.encuesta.forEach((preg,ind)=> {
          //let auxPreg: any = this._AnswersMap[key];
          const {_id,respuestas} = preg;
          //console.log(auxPreg);
          this._answers[`${_id}`]=[...respuestas.filter((p:toBackendAnswer,i) => p.dtousuario._id === this.userLogged)];
          //this._userAnswers[`${_id}`]= {}; 
        });
        this._encuesta.encuesta = [...this._encuesta.encuesta.filter((preg,ind) => this._answers[`${preg._id}`].length === 0)];
        console.log(this._answers);
        this.searchState = 'D';
      },
      (error) => {
        console.log(error);
        this._encuesta = this.testRes.data;
        this._encuesta.encuesta.forEach((preg,ind)=> {
          //let auxPreg: any = this._AnswersMap[key];
          const {_id,respuestas} = preg;
          //console.log(auxPreg);
          this._answers[`${_id}`]=[...respuestas.filter((p:toBackendAnswer,i) => p.dtousuario._id === this.userLogged)];
          //this._userAnswers[`${_id}`]= {}; 
        });
        console.log(this._answers);
        this._encuesta.encuesta = [...this._encuesta.encuesta.filter((preg,ind) => this._answers[`${preg._id}`].length === 0)];
        //this._estudio = testRes.data;
        //console.log(this._estudio);
        /*this._estudio.encuesta.forEach((preg, ind) => {
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
        });*/
        this.searchState = 'D';
      }
    );
  }
  ngOnInit(): void {
    this.userLogged = parseInt(JSON.parse(localStorage.getItem('user_data'))['_id'],10);
    this._Id = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    this.searchState = '';
    this.opStatus = 'S';
    this.getData(this._Id)
  }
  
  onDir(_route: string): void {
    try {
      //console.log(_route);
      this.router.navigate([_route]);
    } catch (e) {
      console.log(e.message);
    }
  }
  /*
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
  }*/
  /*
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
  }*/
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
  /*
  saveSurvey(data) {
    this.opStatus = 'P';
    this._respuestaService.saveSurvey(data).subscribe(
      (response: any) => {
        console.log(response);
        this.opStatus = 'D';
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
      }
    );
  }*/
  /*setBackendAnswer(simple, respuesta, encuestaEstudio, usuario, opcion) {
    let singleAnswer = new toBackendAnswer({ _id: 0 }, { _id: 0 });
    singleAnswer.dtoEncuestaEstudio._id = encuestaEstudio;
    singleAnswer.dtousuario._id = usuario;
    if (simple === true) {
      singleAnswer.dtoopcion = opcion;
    } else {
      singleAnswer.respuesta = respuesta;
    }
    this.toAdd.push(singleAnswer);
  }*/
  //NT: ESTE METODO SOLO SE HABILITA UNA VEZ COMPLETADAS LAS PREGUNTAS EN LA ENCUESTA CON SUS RESPUESTAS
  /*postRespuestas() {
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
  }*/
}
