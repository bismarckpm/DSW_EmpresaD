import { Component, OnInit, ViewChild } from '@angular/core';
import { Solicitud } from '@models/solicitud';
import { MatTableDataSource } from '@angular/material/table';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SolicitudService } from '@core/services/solicitud/solicitud.service';
import { UpdateSolicitudDialogComponent } from '../../../cliente/components/dialogs/upd-solicitud-dialog/update-solicitud-dialog.component';
import { UtilService } from '@core/services/utils/util.service';
import { Marca } from '@models/marca';
import { CategoriaService } from '@core/services/categoria/categoria.service';
import { Ocupacion } from '@core/models/ocupacion';
import { ParroquiaService } from '@core/services/parroquia/parroquia.service';
import { OcupacionService } from '@core/services/ocupacion/ocupacion.service';
import { Parroquia } from '@core/models/parroquia';
import { Pais } from '@core/models/pais';
import { Estado } from '@core/models/estado';
import { Municipio } from '@core/models/municipio';
import { MuestraPoblacion } from '@core/models/muestraPoblacion';
import { Muestra_poblacionService } from '@core/services/muestra_poblacion/muestra_poblacion.service';

interface ResCat {
  _id:number,
  nombre:string,
  subcategorias:any[]
}

@Component({
  selector: 'app-Solicitud',
  templateUrl: './Solicitud.component.html',
  styleUrls: ['./Solicitud.component.css'],
})
export class SolicitudComponent implements OnInit {
  constructor(
    private formBuilder: FormBuilder,
    private _solicitudService: SolicitudService,
    private _solicitudUtilService: UtilService,
    private categoriaService: CategoriaService,
    private _parroquiaService: ParroquiaService,
    private _poblacionService: Muestra_poblacionService,
    private _ocupacionService: OcupacionService
  ) {
  }
  sec: string = 'SOL';
  // CONTROL DE ESTADO DEL COMPONENTE
  op: string;
  searchState: string; // U.I,D
  toSearch2: any = {};
  solicitudes: Solicitud[] = [];
  solicitudes2: Solicitud[] = [];
  poblacionForm: FormGroup;
  ocupaciones: Ocupacion[] = [];
  parroquias: Parroquia[] = [];
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
  ID = parseInt(localStorage.getItem('_id'));
  //ID = 5;
  marcas: Marca[] = [];
  presentaciones: any[] = [];
  tipos: any[] = [];
  subcategorias: any[] = [];
  categorias: ResCat[] = [];
  minAge: number = 0;
  maxAge: number = 0;
  

  // COLUMNAS DE TABLA DE RESULTADOS
  displayedColumns: string[] = [
    'selector',
    'estado',
    'Marca',
    'presentacion',
    'subcategoria',
    'tipos',
    'ops',
  ];
  columnsToDisplay: string[] = this.displayedColumns.slice();

  // INDICE DE SOLICITUD SELECCIONADO
  solicitudSelection = 0;

  // LISTA DE SOLICITUDES DEVUELTOS EN BÚSQUEDA
  dataSource: MatTableDataSource<Solicitud>;
  solicitudTarget: Solicitud;
  // FORMULARIOS
  clienteUser: any = JSON.parse(localStorage.getItem('user_data'));
  searchForm: FormGroup;
  searchModel: Solicitud;
  addForm: FormGroup;
  opStatus: string; // S,P,D
  userSolicitud: number;
  @ViewChild('updSolicitud') private updComponent: UpdateSolicitudDialogComponent;

  setUsuarioSolicitud(U: number) {
    this.userSolicitud = U;
  }

  getCategorias() {
    this.categoriaService.getCategorias().subscribe(
      (response) => {
        console.log(response);
        this.categorias = response.data;
      },
      (error) => {
        console.log(error);
        let testCategorias: any = {
          "status": 200,
          "data": [
            {
              "_id": 1,
              "nombre": "Muebles de dormitorio",
              subcategorias: []
            },
            {
              "_id": 2,
              "nombre": "Maquillaje",
              subcategorias: []
            },
            {
              "_id": 3,
              "nombre": "Electronicos",
              subcategorias: [
                {
                  "_id": 3,
                  "nombre": "Computadoras de escritorio",
                  "tipos": [
                    {
                      "_id": 3,
                      "nombre": "Liquido",
                      "presentaciones": [
                        {
                          "_id": 3,
                          "tipo": "peso",
                          "Cantidad": "1000 g"
                        }
                      ]
                    }
                  ]
                },
                {
                  "_id": 4,
                  "nombre": "TVs",
                  "tipos": [
                    {
                      "_id": 4,
                      "nombre": "Polvo",
                      "presentaciones": [
                        {
                          "_id": 4,
                          "tipo": "volumen",
                          "Cantidad": "2 l"
                        }
                      ]
                    }
                  ]
                }
              ]
            },
            {
              "_id": 4,
              "nombre": "Utiles escolares",
              subcategorias: []
            },
            {
              "_id": 5,
              "nombre": "Vestimenta",
              subcategorias: [
                {
                  "_id": 9,
                  "nombre": "Vestidos",
                  "tipos": []
                }
              ]
            },
            {
              "_id": 6,
              "nombre": "Libros",
              subcategorias: [
                {
                  "_id": 5,
                  "nombre": "Libros de fantasia",
                  "tipos": [
                    {
                      "_id": 5,
                      "nombre": "Solvente",
                      "presentaciones": [
                        {
                          "_id": 5,
                          "tipo": "peso",
                          "Cantidad": "2000 g"
                        }
                      ]
                    }
                  ]
                },
                {
                  "_id": 6,
                  "nombre": "Poesia",
                  "tipos": [
                    {
                      "_id": 6,
                      "nombre": "Spray",
                      "presentaciones": [
                        {
                          "_id": 6,
                          "tipo": "vestimenta",
                          "Cantidad": "M"
                        }
                      ]
                    }
                  ]
                },
                {
                  "_id": 7,
                  "nombre": "Politica",
                  "tipos": [
                    {
                      "_id": 7,
                      "nombre": "formal",
                      "presentaciones": [
                        {
                          "_id": 7,
                          "tipo": "volumen",
                          "Cantidad": "1.5 ml"
                        }
                      ]
                    }
                  ]
                },
                {
                  "_id": 8,
                  "nombre": "Politica",
                  "tipos": [
                    {
                      "_id": 8,
                      "nombre": "Informal",
                      "presentaciones": [
                        {
                          "_id": 8,
                          "tipo": "peso",
                          "Cantidad": "200 kg"
                        }
                      ]
                    }
                  ]
                }
              ]
            },
            {
              "_id": 7,
              "nombre": "Salud",
              subcategorias: []
            },
            {
              "_id": 8,
              "nombre": "Limpieza",
              subcategorias: [
                {
                  "_id": 1,
                  "nombre": "Para dormitorios",
                  "tipos": [
                    {
                      "_id": 1,
                      "nombre": "Barra",
                      "presentaciones": [
                        {
                          "_id": 1,
                          "tipo": "volumen",
                          "Cantidad": "2 l"
                        },
                        {
                          "_id": 9,
                          "tipo": "vestimenta",
                          "Cantidad": "XL"
                        }
                      ]
                    }
                  ]
                },
                {
                  "_id": 2,
                  "nombre": "Para salas",
                  "tipos": [
                    {
                      "_id": 2,
                      "nombre": "Eerosol",
                      "presentaciones": [
                        {
                          "_id": 2,
                          "tipo": "vestimenta",
                          "Cantidad": "SS"
                        },
                        {
                          "_id": 10,
                          "tipo": "vestimenta",
                          "Cantidad": "L"
                        }
                      ]
                    }
                  ]
                }
              ]
            }
          ]
        }
        //
        this.categorias = testCategorias.data;
        /*testCategorias.data.forEach((p,ind) => {
          this.categorias.push(p);
        })
        this.nVar = testCategorias.data;*/
      }
    );
  }
  getParroquias() {
    //OBTENCION DE PARROQUIAS REGISTRADAS
    this._parroquiaService.getParroquias().subscribe(
      (response) => {
        this.parroquias = response.data;
      },
      (error) => {
        this.parroquias = [
          this.testParroquia,
          {
            _id: 6,
            nombre: 'Eglise Notre Dame De Rumengol',
            valorSocioEconomico: 3,
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
        ];
      }
    );
  }
  getOcupaciones() {
    //OBTENCION DE OCUPACIONES REGISTRADAS
    this._ocupacionService.getOcupaciones().subscribe(
      (response) => {
        this.ocupaciones = response.data;
      },
      (error) => {
        console.log(<any>error);
        this.ocupaciones = [{ _id: 1, nombre: 'Ocupacion Test' }];
      }
    );
  }

  addSolicitud(data) {
    this._solicitudService.createSolicitud(data).subscribe(
      (response: any) => {
        console.log(response);
        if (response.status === 200) {
          this.opStatus = 'D';
          // Se hace lo que se quiera en exito
          //alert(response.message);
        }
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
        console.log(data);
      }
    );
  }

  updateSolicitud(id, data) {
    this._solicitudService.updateSolicitud(id, data).subscribe(
      (response) => {
        console.log(response);
        //alert('Se modifico la solicitud correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }

  deleteSolicitud(id, data) {
    this._solicitudService.deleteSolicitud(id, data).subscribe(
      (response) => {
        console.log(response);
        //alert('Se elimino la solicitud correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }
  setTipos(_t){
    if(_t !== ''){
      let parsedInd = parseInt(_t,10);
      this.tipos = this.subcategorias[parsedInd].tipos;
      this.addForm.get('subCategoria').setValue(this.subcategorias[parsedInd]._id);
      //console.log(this.tipos,parsedInd,_t,this.subcategorias[parsedInd]);
      this.presentaciones = [];
    }
    else{
      console.log('Null catcher... PreTipos');
    }
  }
  setPresentaciones(_sc){
    if(_sc !== ''){
      let parsedInd = parseInt(_sc,10);
      this.presentaciones = this.tipos[parsedInd].presentaciones;
      this.addForm.get('tipo').setValue(this.tipos[parsedInd]._id);
      //console.log(this.presentaciones,parsedInd,_sc,this.tipos[parsedInd]);
    }
    else{
      console.log('Null catcher...PrePresentaciones');
    }
  }
  setSubCategorias(_c){
    //this.subcategorias = categoria.subcategorias;
    if(_c !== ''){
      let parsedInd = parseInt(_c,10);
      this.subcategorias = this.categorias[parsedInd].subcategorias;
      console.log(this.subcategorias,_c,this.categorias[parsedInd]);
      this.tipos = [];
      this.presentaciones = [];
    }
    else{
      console.log('Null catcher...PreSubCat');
    }
  }
  ngOnInit(): void {
    this.poblacionForm = this.formBuilder.group({
      genero: null,
      nivelEconomico: null,
      nivelAcademico: null,
      rangoEdadInicio: null,
      rangoEdadFin: null,
      cantidadHijos: null,
      fk_lugar: null,
      fk_ocupacion: null,
    });
    this.addForm = this.formBuilder.group({
      marca: null,
      presentacion: null,
      usuario:JSON.parse(localStorage.getItem('user_data'))['_id'],
      tipo: null,
      categoria:null,
      subCategoria: null,
      estado: 'solicitada',
      comentarios:null,
      muestraPoblacion:null,
    });
    this.searchForm = this.formBuilder.group({
      usuario: null, // SELECT
      presentacion: null,
      subCategoria: null,
      tipo: null,
      marca: null, // SELECT
      estado: null, // SELECT
      activo: null, // CHECKBOX O SELECT
      creado_el: null, // DATE TO STRING
      modificado_el: null, // DATE TO STRING
    });
    this.setOperation('');
    this.searchState = 'U';
    this.getCategorias();
    this.getOcupaciones();
    this.getParroquias();
  }

  async openUpdModal() {
    return await this.updComponent.open();
  }

  getYearDiff(t: string): number {
    let _t = new Date(t);
    let _n: Date = new Date(Date.now());
    return _n.getFullYear() - _t.getFullYear();
  }
  ageEdit(min, max) {
    let _auxDate: Date = new Date(Date.now());

    if (min !== null) {
      //console.log('Edad minima ',min);
      this.minAge = min;
      let newMin: Date = new Date(
        _auxDate.getFullYear() - min,
        _auxDate.getMonth(),
        _auxDate.getDay()
      );
      console.log(newMin);
      this.poblacionForm.get('rangoEdadInicio').setValue(newMin);
    } else {
      //console.log('Edad máxima ',max);
      this.maxAge = max;
      let newMax: Date = new Date(
        _auxDate.getFullYear() - max,
        _auxDate.getMonth(),
        _auxDate.getDay()
      );
      console.log(newMax);
      this.poblacionForm.get('rangoEdadFin').setValue(newMax);
    }
  }

  verifySolicitud(){
    if (this.addForm.valid) {
      console.log(this.addForm.value);
      this.sec='POB';
    }
    else{

    }
  }
  verifyPoblacion(){
    if(this.poblacionForm.valid){
      this.opStatus = 'P';
      const {presentacion,estado,_id,usuario,...rest} = this.addForm.value;
      let toAddPoblacion:any = {
        ...this.poblacionForm.value
      };
      console.log(toAddPoblacion.value);
      let toAddSolicitud: any = {
        estado:estado,
        usuario:usuario,
        comentarios:null,
        presentaciones:[presentacion],
        muestraPoblacion:null
      };
      this._poblacionService.createMuestraPoblacion(toAddPoblacion).subscribe(
      (res:any)=>{
        toAddSolicitud.muestraPoblacion = res._id;
        console.log(toAddSolicitud);
        this.addSolicitud(toAddSolicitud);
      },
      (err)=>{
        toAddSolicitud.muestraPoblacion = 1;
        console.log(toAddSolicitud);
        console.log(err);
        this.addSolicitud(toAddSolicitud);
      })
    }
    else {

    }
  }
  serviceInvoke() {
    /*if (this.addForm.valid) {
      // FALTA VALIDACION
      // console.log(this.addForm.value);

      /*const toAdd = {
        usuario: { _id: undefined },
        estado: undefined,
        marca: { _id: undefined },
        tipos: [],
        subCategorias: [],
        presentaciones: [],
      };
      const values = this.addForm.value;
      toAdd.estado = 'solicitada';
      toAdd.usuario._id = this.ID;
      toAdd.marca._id = values.marca;
      values.tipo.forEach(function (item) {
        toAdd.tipos.push({ _id: item });
      });
      values.subCategoria.forEach(function (item) {
        toAdd.subCategorias.push({ _id: item });
      });
      values.presentacion.forEach(function (item) {
        toAdd.presentaciones.push({ _id: item });
      });
      const toAdd = {
        ...this.addForm.value
      };
      console.log(toAdd);
      //this.addSolicitud(toAdd);
      this.opStatus = 'P';
      /*setTimeout(() => {
        this.addForm = this.formBuilder.group({
          marca: 1,
          presentacion: 1,
          usuario: 1,
          tipo: 1,
          subCategoria: 1,
          estado: 'solicitada',
        });
        this.opStatus = 'D';
      }, 3000);
    } else {
      
    }*/
  }

  selectSolicitud(id: number, data: Solicitud) {
    if (id === this.solicitudSelection) {
      this.solicitudSelection = 0;
      this.solicitudTarget = null;
    } else {
      this.solicitudSelection = id;
      this.solicitudTarget = data;
    }
  }

  isSelected(id: number): boolean {
    if (id === this.solicitudSelection) {
      return true;
    }
    return false;
  }

  dataFilter(dataArray: Solicitud[]): Solicitud[] {
    // console.log(this.searchForm.value);
    const filtered: Solicitud[] = [];
    dataArray.forEach((res, ind) => {
      let inc = true;
      // tslint:disable-next-line:variable-name
      Object.entries(this.searchForm.value).forEach(([key, field], _ind) => {
        if (inc === true && field !== null) {
          if (
            field instanceof Date &&
            res[key] >= field &&
            res[key] <= Date.now()
          ) {
            return;
          } else if (typeof field === 'string' && res[key].startsWith(field)) {
            return;
          } else if (typeof field === 'boolean' && res[key] === field) {
            return;
          } else {
            inc = false;
          }
        }
      });
      if (inc === true) {
        filtered.push(res);
      }
    });
    // console.log(dataArray, filtered);
    return filtered;
  }

  getSolicitudes(data) {
    this._solicitudUtilService.getSolicitudesOfCliente(this.clienteUser._id).subscribe(
      (response: any) => {
        console.log(response);
        this.solicitudes = response.data;
        this.dataSource = new MatTableDataSource<Solicitud>(
          this.dataFilter(this.solicitudes)
        );
        this.searchState = 'D';
      },
      (error) => {
        console.log(error);
      }
    );
  }

  invokeSearch() {
    this.solicitudes = [];
    this.solicitudSelection = 0;
    if (this.searchForm.value.creado_el !== null) {
      this.searchForm
        .get('creado_el')
        .setValue(new Date(this.searchForm.value.creado_el));
    }
    if (this.searchForm.value.modificado_el !== null) {
      this.searchForm
        .get('modificado_el')
        .setValue(new Date(this.searchForm.value.modificado_el));
    }
    // this.searchForm.get('');
    this.searchState = 'P';
    const toSearch: any = {};
    const values = this.searchForm.value;
    toSearch.tipo = values.tipo;
    (toSearch._id = 1), (toSearch.subCategoria = values.subCategoria);
    toSearch.marca = values.marca;
    toSearch.presentacion = values.presentacion;
    this.getSolicitudes(toSearch);
  }

  setOperation(chOp: string) {
    this.op = chOp;
    // console.log(this.searchState);
    if (chOp !== '') {
      this.searchState = 'I';
      this.opStatus = 'S';
      this.setUsuarioSolicitud(null);
    } else {
      this.searchState = 'U';
    }
  }

  doSearch() {
    this.searchState = 'I';
  }
}
