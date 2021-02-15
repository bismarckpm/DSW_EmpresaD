import { Component, OnInit, ViewChild } from '@angular/core';
import { EstudioService } from '@core/services/estudio/estudio.service';
import { Muestra_poblacionService } from '@core/services/muestra_poblacion/muestra_poblacion.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MuestraPoblacion } from '@models/muestraPoblacion';
import { UtilService } from '@core/services/utils/util.service';
import { Estudio } from '@models/estudio';
import { BasicInfoDialogComponent } from '../../components/dialogs/basic-info-dialog/basic-info-dialog.component';
import { EncuestaDialogComponent } from '../../components/encuesta-dialog/encuesta-dialog.component';
import { EstudioResultadoDialogComponent } from '../../components/estudio-resultado-dialog/estudio-resultado-dialog.component';
import { toBackendAnswer } from '@core/models/toBackendAnswer';

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
  _targetEncuestado: any = null;
  _AnswersMap: any = {};
  _userAnswers: any = {};
  constructor(
    private route: ActivatedRoute,
    private _estudioService: EstudioService,
    private _poblacionService: Muestra_poblacionService,
    private _utilsService: UtilService
  ) {}
  @ViewChild('info') private infoComponent: BasicInfoDialogComponent;
  async openInfoModal() {
    return await this.infoComponent.open();
  }
  @ViewChild('pobInfo') private pobInfoComponent: BasicInfoDialogComponent;
  async openPobInfoModal() {
    return await this.pobInfoComponent.open();
  }
  @ViewChild('encuesta') private encuestaComponent: EncuestaDialogComponent;
  async openEncuestaModal() {
    return await this.encuestaComponent.open();
  }
  @ViewChild('respDiag')
  private respDiagComponent: EstudioResultadoDialogComponent;
  async openRespDiagModal() {
    return await this.respDiagComponent.open();
  }

  alterAnswersState(event: {
    user: any,
    data: toBackendAnswer[],
    _pregId: number,
  }) {
    console.log(event);
    console.log(
      this._AnswersMap[`${this._Id}-${event._pregId}`].respuestas,
      ' :B'
    );
    const auxResp: toBackendAnswer[] = event.data;
    for(const ans of auxResp){
      this._AnswersMap[`${this._Id}-${event._pregId}`].respuestas.push(ans);
    }
    console.log(
      this._AnswersMap[`${this._Id}-${event._pregId}`].respuestas,
      ' :A'
    );
  }
  testRes: any = {
    status: 200,
    data: {
      _id: 5,
      estado: 'En ejecucion',
      tipo: 'encuesta',
      encuestas_esperadas: 1,
      solicitud: {
        _id: 5,
        estado: 'solicitada',
        usuario: {
          _id: 35,
          nombre: 'Solomon',
          apellido: 'Bentley',
          rol: 'cliente',
          estado: 'activo',
          correo: 'B123@gmail.com',
        },
        marca: 'Sin especificar',
        comentarios: 'Sin comentarios',
        presentaciones: [
          {
            _id: 5,
            tipo: 'Talla',
            Cantidad: 'S',
            fk_tipo: {
              _id: 5,
              nombre: 'Sacos',
              subCategoria: {
                _id: 5,
                nombre: 'Formal',
                categoria: {
                  _id: 5,
                  nombre: 'Vestimenta',
                },
              },
            },
          },
        ],
        muestraPoblacion: {
          _id: 5,
          genero: 'femenino',
          nivel_economico: 'Alto',
          nivel_academico: 'Doctorado',
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
                pais: {
                  _id: 1,
                  nombre: 'Venezuela',
                },
              },
            },
          },
        },
      },
      analista: {
        _id: 40,
        nombre: 'Hammett',
        apellido: 'Schneider',
        rol: 'analista',
        estado: 'activo',
        correo: 'HMLETSCH456@gmail.com',
      },
      encuesta: [
        {
          _id: 13,
          pregunta: {
            _id: 14,
            nombre: 'a quienes lo recomendaria',
            tipo: 'multiple',
            usuario: {
              _id: 28,
              nombre: 'Jonas',
              apellido: 'Mccray',
              rol: 'administrador',
              estado: 'activo',
              correo: 'JON_M@gmail.com',
            },
            opciones: [
              {
                _id: 16,
                nombre: 'amigos',
              },
              {
                _id: 17,
                nombre: 'familiares',
              },
              {
                _id: 18,
                nombre: 'pareja',
              },
              {
                _id: 19,
                nombre: 'conocidos',
              },
            ],
          },
          respuestas: [],
        },
        {
          _id: 14,
          pregunta: {
            _id: 1,
            nombre: 'Que opina del producto? ',
            tipo: 'abierta',
            usuario: {
              _id: 26,
              nombre: 'Macon',
              apellido: 'Mcleod',
              rol: 'administrador',
              estado: 'activo',
              correo: 'MM10@gmail.com',
            },
          },
          respuestas: [
            {
              respuesta: 'respuesta',
              dtoEncuestaEstudio: {
                _id: 22,
              },
              dtousuario: {
                _id: 3,
              },
            },
          ],
        },
        {
          _id: 18,
          pregunta: {
            _id: 7,
            nombre: 'Cuentenos, tuvo algun problema con el producto?',
            tipo: 'simple',
            usuario: {
              _id: 26,
              nombre: 'Macon',
              apellido: 'Mcleod',
              rol: 'administrador',
              estado: 'activo',
              correo: 'MM10@gmail.com',

            },
            opciones:[{"_id":12,"nombre":"redes sociales"}],
          },
          respuestas: [],
        },
        {
          _id: 15,
          pregunta: {
            _id: 2,
            nombre: 'Cuentenos, tuvo algun problema con el producto?',
            tipo: 'abierta',
            usuario: {
              _id: 26,
              nombre: 'Macon',
              apellido: 'Mcleod',
              rol: 'administrador',
              estado: 'activo',
              correo: 'MM10@gmail.com',
            },
          },
          respuestas: [],
        },
      ],
    },
  };
  getYearDiff(t: string): number {
    let _t = new Date(t);
    let _n: Date = new Date(Date.now());
    return _n.getFullYear() - _t.getFullYear();
  }
  mapEncuesta() {
    this._Estudio.encuesta.forEach((preg, ind) => {
      const { respuestas } = preg;
      //console.log(preg.pregunta);
      this._AnswersMap[`${this._Id}-${preg._id}`] = {
        _id: preg._id,
        tipo: preg.pregunta.tipo,
        respuestas,
      };
    });
  }
  ngOnInit(): void {
    this.searchState = 'I';
    this._Id = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    if (this._Id !== 0) {
      this.getEstudio();
    }
  }
  setUsuarioEncuesta(_user, _encuesta) {
    this._targetEncuestado = _user;
    //console.log(this._AnswersMap);
    //console.log(_user);
    Object.keys(this._AnswersMap).forEach((key, ind) => {
      let auxPreg: any = this._AnswersMap[key];
      const { _id, respuestas } = auxPreg;
      //console.log(auxPreg);
      this._userAnswers[`${_id}`] = [
        ...respuestas.filter(
          (p: toBackendAnswer, i) =>
            p.dtousuario._id === this._targetEncuestado._id
        ),
      ];
      //this._userAnswers[`${_id}`]= {};
    });
    this.openEncuestaModal();
  }
  getEncuestadosCanAnswerEstudio(id) {
    this._utilsService.getUsuariosCanApplyToEstudio(id).subscribe(
      (response) => {
        console.log(response.data);
        if (response.status === 200) {
          this._encuestados = [
            ...this._encuestados,
            ...response.data.map((p, ind) => {
              return { ...p, done: false };
            }),
          ];
        } else {
          this._encuestados = [];
        }
      },
      (error) => {
        console.log(error);
        this._encuestados = [
          ...this._encuestados,
          {
            _id: 2,
            nombre: 'Zeus',
            apellido: 'Berg',
            rol: 'encuestado',
            estado: 'activo',
            correo: 'ZB@gmail.com',
            done: false,
          },
          {
            _id: 3,
            nombre: 'No se',
            apellido: 'op',
            rol: 'encuestado',
            estado: 'activo',
            correo: 'ADDG@gmail.com',
            done: false,
          },
          {
            _id: 4,
            nombre: 'Jamal',
            apellido: 'Stevenson',
            rol: 'encuestado',
            estado: 'activo',
            correo: 'jamal_Stevenson@gmail.com',
            done: false,
          },
          {
            _id: 5,
            nombre: 'Camrock',
            apellido: 'soteldo',
            rol: 'encuestado',
            estado: 'activo',
            correo: 'Elmo@gmail.com',
            done: false,
          },
        ];
      }
    );
  }
  getEncuestados(id: number) {
    this._utilsService.getUsuariosOfEncuesta(id).subscribe(
      (res) => {
        console.log(res);
        if (res.status === 200) {
          this._encuestados = res.data.map((p, ind) => {
            return { ...p, done: true };
          });
          console.log(res.data);
          this.getEncuestadosCanAnswerEstudio(id);
        } else {
          this._encuestados = [];
          this.getEncuestadosCanAnswerEstudio(id);
        }
      },
      (err) => {
        console.log(err.message);
        this._encuestados = [
          {
            _id: 102,
            nombre: 'Test nombre 1',
            apellido: 'Test apellido 1',
            correo: 'test@gmail.com',
            estado: 'activo',
            rol: 'encuestado',
            done: true,
          },
        ];
        this.getEncuestadosCanAnswerEstudio(id);
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
        this.mapEncuesta();
      },
      (err) => {
        console.log(err.message);
        this._Estudio = this.testRes['data'];
        this.searchState = 'D';
        this.getEncuestados(this._Id);
        this.mapEncuesta();
      }
    );
  }
}
