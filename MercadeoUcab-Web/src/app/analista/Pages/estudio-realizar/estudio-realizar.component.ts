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
  _Estudio: any;
  _poblacion: MuestraPoblacion;
  _Id: number = 0;
  searchState: string; //I.P,D
  _encuestados: any[] = [];
  constructor(
    private route: ActivatedRoute,
    private _estudioService: EstudioService,
    private _poblacionService: Muestra_poblacionService,
    private _utilsService: UtilService
  ) {}
  testRes = {
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
        Fk_ocupacion: { _id: 1, nombre: 'test Ocupacion' },
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
  ngOnInit(): void {
    this.searchState = 'I';
    this._Id = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    if (this._Id !== 0) {
      this.getEncuestados(2);
      this.getEncuestadosCanAnswerEstudio(2);
    }
  }

  getEncuestadosCanAnswerEstudio(id) {
    this._utilsService.getUsuariosCanApplyToEstudio(id).subscribe(
      (response) => {
        console.log(response.data);
      },
      (error) => {
        console.log(error);
      }
    );
  }
  getEncuestados(id: number) {
    this._utilsService.getUsuariosOfEncuesta(id).subscribe(
      (res) => {
        this._encuestados = res.data;
        console.log(res.data);
      },
      (err) => {
        console.log(err.message);
        this._encuestados = [];
      }
    );
  }
  getEstudio() {
    this.searchState = 'P';
    this._estudioService.getEstudio(this._Id).subscribe(
      (res) => {
        this._Estudio = res.data;
        this.searchState = 'D';
        this.getEncuestados(this._Id);
      },
      (err) => {
        console.log(err.message);
        this._Estudio = this.testRes.data;
        this.searchState = 'D';
        this.getEncuestados(this._Id);
      }
    );
  }
}
