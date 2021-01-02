import { Component, OnInit, } from '@angular/core';
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

export interface PeriodicElement {
  name: string;
  state: string;
}

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
  analistaId: number = 5;
  searchState: string = "U";
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private _estudioService: EstudioService) { }
  //DATA DUMMY
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
  testUser: Usuario = {
    _id: Math.floor(Math.random() * (1000 - 1) + 1),
    nombre: Math.random().toString(36).substr(2, 5),
    apellido: Math.random().toString(36).substr(2, 5),
    rol: 'Analista',
    correo: Math.random().toString(36).substr(2, 5),
    estado: 'Activo',
  }
  testSolicitud: Solicitud = {
    _id: 13,
    estado: 'activo',
    usuario: this.testUser,
    marca: { _id: 1, nombre: 'TEST MARCA' },
    tipos: [{ _id: 1, nombre: 'test Tipo' }],
    presentaciones: [{ _id: 1, tipo: 'volumen', cantidad: '800ml' }],
    subcategorias: [{ _id: 1, nombre: 'test SubCategoria', categoria: { _id: 1, nombre: 'test Categoria' } }]
  }
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
    this.searchState = "I";
    this._estudioService.getEstudios().subscribe(
      (res) =>{
        this.estudios = res.data;
        this.dataSource = new MatTableDataSource<Estudio>(this.dataFilter(this.estudios));
        this.searchState = "D";
       },
      (err) => {
        console.log(err.message);
        this.estudios = [this.testRes.data];
        this.dataSource = new MatTableDataSource<Estudio>(this.dataFilter(this.estudios));
        this.searchState = "D";
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
