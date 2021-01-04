import { Component, OnInit } from '@angular/core';
import { EstudioService } from '@core/services/estudio/estudio.service';
import { Muestra_poblacionService } from '@core/services/muestra_poblacion/muestra_poblacion.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MuestraPoblacion } from '@models/muestraPoblacion';
import { UtilService } from '@core/services/utils/util.service';
import { Estudio } from '@models/estudio';
@Component({
  selector: 'app-estudio-realizar',
  templateUrl: './estudio-realizar.component.html',
  styleUrls: ['./estudio-realizar.component.css'],
  providers: [],
})
export class EstudioRealizarComponent implements OnInit {
  _Estudio: any = null;
  _poblacion: MuestraPoblacion;
  _Id: number = 0;
  searchState: string; //I.P,D
  _encuestados: any[] = [];
  constructor(
    private route: ActivatedRoute,
    private _estudioService: EstudioService,
    private _poblacionService: Muestra_poblacionService,
    private _utilsService: UtilService
  ) { }
  testRes: any = {
    "status": 200,
    "data": {
      "_id": 2,
      "estado": "Culminado",
      "tipo": "En linea",
      "encuestas_esperadas": 20,
      "solicitud": {
        "_id": 2,
        "estado": "solicitada"
      },
      "analista": {
        "_id": 37,
        "nombre": "Harrison",
        "apellido": "Dorsey",
        "correo": "HARRI@gmail.com",
        "rol": "analista"
      },
      "muestra_poblacion": {
        "_id": 2,
        "genero": "masculino",
        "nivel_academico": "licenciado",
        "rango_edad_inicio": 15,
        "rango_edad_fin": 80,
        "cantidad_hijos": 1,
        "parroquia": {
          "_id": 1,
          "nombre": "petare",
          "valorSocioEconomico": 1000,
          "municipio": {
            "_id": 1,
            "nombre": "Libertador",
            "estado": {
              "_id": 1,
              "nombre": "Guarico",
              "pais": {
                "_id": 1,
                "nombre": "Venezuela"
              }
            }
          }
        }
      },
      "encuesta": [
        {
          "_id": 4,
          "pregunta": {
            "_id": 4,
            "nombre": "Como se entero del producto?",
            "tipo": "simple",
            "opciones": [
              {
                "_id": 1,
                "nombre_opcion": "opcion 2: No la comprendo muy bien"
              },
              {
                "_id": 2,
                "nombre_opcion": "radio"
              },
              {
                "_id": 3,
                "nombre_opcion": "TV"
              },
              {
                "_id": 4,
                "nombre_opcion": "conocidos"
              }
            ]
          }
        },
        {
          "_id": 5,
          "pregunta": {
            "_id": 5,
            "nombre": "cuanto uso el producto?",
            "tipo": "simple",
            "opciones": [
              {
                "_id": 5,
                "nombre_opcion": "Mucho"
              },
              {
                "_id": 6,
                "nombre_opcion": "Poco"
              },
              {
                "_id": 7,
                "nombre_opcion": "Nada"
              }
            ]
          }
        },
        {
          "_id": 6,
          "pregunta": {
            "_id": 7,
            "nombre": "Recomendaria el producto?",
            "tipo": "boolean"
          }
        }
      ]
    }
  }
  ngOnInit(): void {
    this.searchState = 'I';
    this._Id = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    if (this._Id !== 0) {
      this.getEstudio();0
    }
  }

  getEncuestadosCanAnswerEstudio(id) {
    this._utilsService.getUsuariosCanApplyToEstudio(id).subscribe(
      (response) => {
        console.log(response.data);
        this._encuestados = [...this._encuestados,response.data.map((p,ind) => { return {...p,done:false} })];
      },
      (error) => {
        console.log(error);
        this._encuestados = [
          ...this._encuestados,{
            "_id":102,
            "nombre": "Test nombre 2",
            "apellido": "Test apellido 2",
            "correo": "test@gmail.com",
            "estado":"activo",
            "rol":"encuestado",
            "done":false,
          }
        ];
      }
    );
  }
  getEncuestados(id: number) {
    this._utilsService.getUsuariosOfEncuesta(id).subscribe(
      (res) => {
        this._encuestados = res.data.map((p,ind) => { return {...p,done:true} });
        console.log(res.data);
      },
      (err) => {
        console.log(err.message);
        this._encuestados = [
          ...this._encuestados,{
            "_id":102,
            "nombre": "Test nombre 1",
            "apellido": "Test apellido 1",
            "correo": "test@gmail.com",
            "estado":"activo",
            "rol":"encuestado",
            "done":true,
          }
        ];
      }
    );
  }
  getEstudio() {
    this.searchState = 'P';
    this._encuestados = [];
    this._estudioService.getEstudio(this._Id).subscribe(
      (res) => {
        this._Estudio = res.data;
        this.searchState = 'D';
        this.getEncuestados(this._Id);
        this.getEncuestadosCanAnswerEstudio(this._Id);
      },
      (err) => {
        console.log(err.message);
        this._Estudio = this.testRes["data"];
        this.searchState = 'D';
        this.getEncuestados(this._Id);
        this.getEncuestadosCanAnswerEstudio(this._Id);
      }
    );
  }
}
