import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Respuesta } from '@models/respuesta';
import { Encuesta } from '@models/encuesta';
import { Pregunta } from '@models/pregunta';
import { Opcion } from '@models/opcion';
import { Estudio } from '@models/estudio';
import { Solicitud } from '@models/solicitud';
import { Usuario } from '@models/usuario';
import { Respuesta } from '@models/respuesta';
import { MuestraPoblacion } from '@models/muestraPoblacion';
import { RespuestaService } from '@core/services/respuesta/respuesta.service';
import { PreguntaService } from '@core/services/pregunta/pregunta.service';
import { EstudioService } from '@core/services/estudio/estudio.service';
@Component({
  selector: 'app-responder-encuesta',
  templateUrl: './responder-encuesta.component.html',
  styleUrls: ['./responder-encuesta.component.css']
})
export class ResponderEncuestaComponent implements OnInit {
  _Id:number = 0;
  _preguntas: Pregunta[] = [];
  _estudio: Estudio;
  _respuestas: Respuesta[] = [];
  searchState:string = "";
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
      pregunta:'abierta',
      tipo:'',
      rango:'',
      opciones: [],
      usuario: this.sampleUsuario,
    },
    {
      _id:2,
      pregunta:'multiple',
      tipo:'',
      rango:'',
      opciones: [],
      usuario: this.sampleUsuario,
    },
    {
      _id:3,
      pregunta:'simple',
      tipo:'',
      rango:'',
      opciones: [],
      usuario: this.sampleUsuario,
    },
    {
      _id:4,
      pregunta:'boolean',
      tipo:'',
      rango:'',
      opciones: [],
      usuario: this.sampleUsuario,
    },
    {
      _id:5,
      pregunta:'rango',
      tipo:'',
      rango:'',
      opciones: [],
      usuario: this.sampleUsuario,
    }, 
  ];
  getPreguntas(){
    
  }
  postRespuestas(){
    //let _respuesta: Respuesta;
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

  setRespuesta(pregPos:number,tipoPreg:string,resp:any){
    switch(tipoPreg){
      case 'abierta':
        break;
      case 'simple':
        break;
      case 'multiple':
        break;
      case 'boolean':
        break;
      case 'rango':
        break;
    }
  }
}
