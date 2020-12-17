import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Respuesta } from '@models/respuesta';
import { Encuesta } from '@models/encuesta';
import { Pregunta } from '@models/pregunta';
import { Opcion } from '@models/opcion';
import { Estudio } from '@models/estudio';
import { Solicitud } from '@models/solicitud';
import { Usuario } from '@models/usuario';
import { MuestraPoblacion } from '@models/muestraPoblacion';
import { RespuestaService } from '@core/services/respuesta/respuesta.service';
import { PreguntaService } from '@core/services/pregunta/pregunta.service';
import { EstudioService } from '@core/services/estudio/estudio.service';

//MODELO DE RESPUESTA CONTROLADA
//los valores *_val corresponden al campo alterable SEGUN EL TIPO DE PREGUNTA
//siendo * siempre la primera letra deL tipo de pregunta a responder
interface RespuestaModel {
  tipo:string;
  a_val:string | null;
  s_val:Opcion | null;
  m_val:Opcion[];
  b_val:boolean | null;
  r_val:number | null;
  done:boolean;
}


@Component({
  selector: 'app-responder-encuesta',
  templateUrl: './responder-encuesta.component.html',
  styleUrls: ['./responder-encuesta.component.css']
})
export class ResponderEncuestaComponent implements OnInit {
  _Id:number = 0;
  _preguntas: Pregunta[] = [];
  _estudio: Estudio = null;
  _respuestas: RespuestaModel[] = [];
  _sampleOption:Opcion = {_id:0,nombre_opcion:''};
  searchState:string = "";
  surveyReady:boolean=false;
  constructor(
    private route: ActivatedRoute,
    private router:Router,
    private _respuestaService: RespuestaService,
    private _preguntaService: PreguntaService,
    private _estudioService: EstudioService,
    ){}
 testPais = {
        _id:1,
        nombre:'Test pais'
    };
 testEstado = {
        _id:1,
        nombre:'Test estado',
        pais:this.testPais,
    };
 testMunicipio = {
       _id:1,
      nombre:'Test  municipio',
      estado:this.testEstado,
    }
 testParroquia = { 
       _id:1,
      nombre:'Test  parrroquia',
      municipio:this.testMunicipio,
      valorSocioEconomico:8000
    }
  sampleUsuario : Usuario = {
         _id:Math.floor(Math.random()*(1000-1)+1),
         nombre:Math.random().toString(36).substr(2, 5),
         apellido:Math.random().toString(36).substr(2, 5),
         rol:'Administrador',
         correo:Math.random().toString(36).substr(2, 5),
         estado:'Activo',
      };
  samplePoblacion: MuestraPoblacion = {
    _id:1,
    genero:'U',
    nivel_economico:999,
    nivel_academico: 'ABCD',
    rango_edad_inicio: 25,
    rango_edad_fin: 50,
    cantidad_hijos: 0,
    parroquia: this.testParroquia,
  };
  sampleSolicitud: Solicitud = {
        _id:1,
        estado:'activo',
        usuario: this.sampleUsuario,
        marca: {
          _id:3,
          nombre:'MARCA'
        },
        tipos:[],
        presentaciones:[],
        subcategorias: []
  };
  samplePreguntas : Pregunta[] = [
    {
      _id:1,
      pregunta:'preg abierta',
      tipo:'abierta',
      rango:'',
      opciones: [],
      usuario: this.sampleUsuario,
    },
    {
      _id:2,
      pregunta:'preg multiple',
      tipo:'multiple',
      rango:'',
      opciones: [
      {_id:1,nombre_opcion:'test mult 1'},
      {_id:2,nombre_opcion:'test mult 2'},
      ],
      usuario: this.sampleUsuario,
    },
    {
      _id:3,
      pregunta:'preg simple',
      tipo:'simple',
      rango:'',
      opciones: [
      {_id:1,nombre_opcion:'test simple 1'},
      {_id:2,nombre_opcion:'test simple 2'},],
      usuario: this.sampleUsuario,
    },
    {
      _id:4,
      pregunta:'preg boolean',
      tipo:'boolean',
      rango:'',
      opciones: [],
      usuario: this.sampleUsuario,
    },
    {
      _id:5,
      pregunta:'preg rango',
      tipo:'rango',
      rango:'1&100',
      opciones: [],
      usuario: this.sampleUsuario,
    }, 
  ];
  getPreguntas(){
    
  }
  setRespuestas(pregInd,tipoPreg,data){
    //let _respuesta: Respuesta;
      switch(tipoPreg){
      case 'abierta':
        this._respuestas[pregInd].a_val = data;
        break;
      case 'simple':
        if(parseInt(data,10) - 1 === -1 ){
          this._respuestas[pregInd].s_val = null;
        }
        else{
          this._respuestas[pregInd].s_val = this._estudio.preguntas[pregInd].opciones[parseInt(data,10)-1];
        }
      case 'boolean':
        this._respuestas[pregInd].b_val = data;
        break;
      case 'rango':
        if(data !== ''){
          this._respuestas[pregInd].r_val = parseInt(data,10) ;
        }
        else{
          this._respuestas[pregInd].r_val = parseInt(data,10) ; 
        }
        break;
      }
      //console.log(this._respuestas[pregInd]);
  }
  setMultOption(pregId,op){
    let ILength: number = this._respuestas[pregId].m_val.length;
    this._respuestas[pregId].m_val = this._respuestas[pregId].m_val.filter(regOp => regOp._id !== op._id);
    if(ILength === this._respuestas[pregId].m_val.length){
      this._respuestas[pregId].m_val.push(op);
    }
    //console.log(this._respuestas[pregId].m_val);
  }
  checkMultiple(pregInd:number,opInd:number){
    let res: boolean = false;
    this._respuestas[pregInd].m_val.forEach((opc,ind) => {
      if(opc._id === opInd){
        res = true;
      }
    });
    //console.log(res,` for ${opInd}`);
    return res;
  }
  //NT: NO FUE POSIBLE EL PASO DEL ESTUDIO A RESPONDER DESDE LA TABLA DE ESTUDIO ASIGNADOS
  //POR LO QUE SE USA UN ID EXTRAIDO DE LA URL EMPLEADA PARA BUSCAR LA INFO DEL ESTUDIO A RESPONDER
  //EN BSD
  getData(estudioId:number){
    this.searchState="P";
    console.log('GETTING DATA FROM: ', estudioId);  
    this._estudioService.getEstudio(estudioId).subscribe(
      (response) => {
        console.log(response);
        this._estudio=response.data;
        this.searchState="D";
      },
      (error) => {
        console.log(error);
         this._estudio={
          _id:666,
          estado:'activo',
          tipo:'WEB',
          encuestas_esperadas:333,
          solicitud:this.sampleSolicitud,
          muestra_poblacion:this.samplePoblacion,
          analista:this.sampleUsuario,
          preguntas:this.samplePreguntas,
         };
         this._estudio.preguntas.forEach((preg,ind)=>{  
            //INSTANCIAR PREPARACION DE Respuesta
            this._respuestas.push({ 
              tipo:preg.tipo,
              a_val:null,
              s_val:null,
              m_val:[],
              b_val:true,
              r_val:null,
              done:false,
            });
         });
         this.searchState="D";
      }
    )
  }
  ngOnInit(): void {
    console.log(this.route.snapshot.paramMap.get('id'));
    this._Id = parseInt(this.route.snapshot.paramMap.get('id'),10);
    this.searchState="";
    this.getData(this._Id);
  } 

  onDir(_route:string):void {
    try{
    //console.log(_route);
      this.router.navigate([_route]);
    }catch(e){
      console.log(e.message);
    }
  }

  checkResp(pregInd,preg){
    let resp: RespuestaModel = this._respuestas[pregInd];
    let _done = false; 
    switch(preg.tipo){
      case 'abierta':
        if(resp.a_val !== null){
          _done  = true;
        }
        //SEND VALUE
        break;
      case 'simple':
        if(resp.s_val !== null){
          _done  = true;
        }
        //SEND OPCION
        break;
      case 'multiple':
      if(resp.m_val !== []){
          _done  = true;
        }
        //FOR EACH SEND
        break;
      case 'boolean':
        _done  = true;
        //SEND
        break;
      case 'rango':
        if(resp.r_val !== null && (  resp.r_val >= parseInt(preg.rango.split('&')[0],10) && resp.r_val <= parseInt(preg.rango.split('&')[1],10))){
          _done  = true;
        }
        //SEND
        break;
    }
    this._respuestas[pregInd].done = _done;
  }
  checkSurvey():boolean{
    let res : boolean = false;
    console.log('Checking answers...');
    this._estudio.preguntas.forEach((preg,ind) =>{
      this.checkResp(ind,preg);
    })

    for(let resp of this._respuestas){
      if(resp.done !== true){
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
  //NT: ESTE METODO SOLO SE HABILITA UNA VEZ COMPLETADAS LAS PREGUNTAS EN LA ENCUESTA CON SUS RESPUESTAS
  postRespuestas(){
    //ARRAY DE RESPUESTAS ESPERADAS Y VALIDADAS 
    //this._respuestas

    //PREGUNTAS DEL ESTUDIO CON CARACTERISTICAS ESPECIFICADAS
    //this._estudio.preguntas 

    //SI SE RECORRE RESPUESTA SE DESCONOCERA EL TIPO DE PREGUNTA, SI SE RECORREN PREGUNTAS Y POR INDICE DE
    //LA PREGUNTA SE BUSCA LA RESPUESTA ASOCIADA (ES EL MISMO) SE PODRA CONOCER EL TIPO Y DETERMINAR EL 
    //ENVIO DE LA RESPUESTA DE ESA MANERA
  }
}