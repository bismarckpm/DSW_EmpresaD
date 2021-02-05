import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EstudioService } from '@core/services/estudio/estudio.service';
import { MatTableDataSource } from '@angular/material/table';
import { Estudio } from '@models/estudio';
import { Parroquia } from '@core/models/parroquia';
import { Municipio } from '@core/models/municipio';
import { Estado } from '@core/models/estado';
import { Pais } from '@core/models/pais';
import { Usuario } from '@core/models/usuario';
import { Solicitud } from '@core/models/solicitud';
import { UtilService } from '@core/services/utils/util.service';
import { BasicInfoDialogComponent } from '../../components/dialogs/basic-info-dialog/basic-info-dialog.component';

@Component({
  selector: 'app-analista-tasks',
  templateUrl: './analista-tasks.component.html',
  styleUrls: ['./analista-tasks.component.css'],
})
export class AnalistaTasksComponent implements OnInit {
  displayedColumns: string[] = ['id', 'expect', 'estado'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  estudios: any[] = [];
  dataSource: MatTableDataSource<Estudio>;
  analistaId: number = parseInt(localStorage.getItem('_id'));
  searchState: string = 'U';
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private _utilService: UtilService,
    private _estudioService: EstudioService
  ) {}
  @ViewChild('info') private infoComponent: BasicInfoDialogComponent;
  async openInfoModal() {
    return await this.infoComponent.open();
  }
  //DATA DUMMY
  /*testPais: Pais = {
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
  testUser: Usuario = {
    _id: Math.floor(Math.random() * (1000 - 1) + 1),
    nombre: Math.random().toString(36).substr(2, 5),
    apellido: Math.random().toString(36).substr(2, 5),
    rol: 'Analista',
    correo: Math.random().toString(36).substr(2, 5),
    estado: 'Activo',
  };
  testSolicitud: Solicitud = {
    _id: 13,
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
  };
  testRes = {
    status: 200,
    data: {
      _id: 2,
      estado: 'Culminado',
      tipo: 'En linea',
      encuestas_esperadas: 20,
      solicitud: {
        _id: 2,
        estado: 'solicitada',
      },
      analista: {
        _id: 37,
        nombre: 'Harrison',
        apellido: 'Dorsey',
        correo: 'HARRI@gmail.com',
        rol: 'analista',
      },
      muestra_poblacion: {
        _id: 2,
        genero: 'masculino',
        nivel_academico: 'licenciado',
        rango_edad_inicio: 15,
        rango_edad_fin: 80,
        cantidad_hijos: 1,
        parroquia: {
          _id: 1,
          nombre: 'petare',
          valorSocioEconomico: 1000,
          municipio: {
            _id: 1,
            nombre: 'Libertador',
            estado: {
              _id: 1,
              nombre: 'Guarico',
              pais: {
                _id: 1,
                nombre: 'Venezuela',
              },
            },
          },
        },
      },
      encuesta: [
        {
          _id: 4,
          pregunta: {
            _id: 4,
            nombre: 'Como se entero del producto?',
            tipo: 'simple',
            opciones: [
              {
                _id: 1,
                nombre_opcion: 'opcion 2: No la comprendo muy bien',
              },
              {
                _id: 2,
                nombre_opcion: 'radio',
              },
              {
                _id: 3,
                nombre_opcion: 'TV',
              },
              {
                _id: 4,
                nombre_opcion: 'conocidos',
              },
            ],
          },
        },
        {
          _id: 5,
          pregunta: {
            _id: 5,
            nombre: 'cuanto uso el producto?',
            tipo: 'simple',
            opciones: [
              {
                _id: 5,
                nombre_opcion: 'Mucho',
              },
              {
                _id: 6,
                nombre_opcion: 'Poco',
              },
              {
                _id: 7,
                nombre_opcion: 'Nada',
              },
            ],
          },
        },
        {
          _id: 6,
          pregunta: {
            _id: 7,
            nombre: 'Recomendaria el producto?',
            tipo: 'boolean',
          },
        },
      ],
    },
  };*/

  ngOnInit(): void {
    //SERVICE INVOKE
    this.invokeService();
  }
  dataFilter(dataArray: Estudio[]): Estudio[] {
    let filtered: Estudio[] = [];
    dataArray.forEach((res, ind) => {
      /*if(res._id === this.analistaId){
        filtered.push(res);
      }*/
      filtered.push(res);
    });
    console.log(dataArray, filtered);
    return filtered;
  }
  invokeService() {
    this.searchState = 'I';
    this._utilService.getEstudiosOfAnalista(this.analistaId).subscribe(
      (res) => {
        this.estudios = res.data;
        this.dataSource = new MatTableDataSource<Estudio>(
          this.dataFilter(this.estudios)
        );
        this.searchState = 'D';
      },
      (err) => {
        console.log(err.message);
        //this.estudios = [this.testRes['data']];
        this.dataSource = new MatTableDataSource<Estudio>(
          this.dataFilter(this.estudios)
        );
        this.searchState = 'D';
      }
    );
  }

  onDirEstudio(_route: string, Id: number): void {
    try {
      this.router.navigate([`analista/estudio/${Id}`]);
    } catch (e) {
      console.log(e.message);
    }
  }
}
