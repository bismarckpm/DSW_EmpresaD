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

interface RespuestaModel {
  tipo:string;
  a_val:string | null;
  s_val:Opcion | null;
  m_val:Opcion[];
  b_val:boolean | null;
  r_val:string | null;
}


@Component({
  selector: 'app-responder-encuesta',
  templateUrl: './responder-encuesta.component.html',
  styleUrls: ['./responder-encuesta.component.css']
})
export class ResponderEncuestaComponent implements OnInit {
  _Id:number = 0;
  _preguntas: Pregunta[] = [];
  _estudio: Estudio;
  _respuestas: RespuestaModel[] = [];
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
        this._respuestas[pregInd].s_val = data;
      case 'boolean':
        this._respuestas[pregInd].b_val = data;
        break;
      case 'rango':
        this._respuestas[pregInd].r_val = data;
        break;
      }
      console.log(this._respuestas[pregInd]);
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
  checkSurvey():boolean{
    let res : boolean = true;
    return res;
  }
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
  }
}