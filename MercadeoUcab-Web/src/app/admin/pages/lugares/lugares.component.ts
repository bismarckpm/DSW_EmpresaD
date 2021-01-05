import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DelLugarDialogComponent } from '../../components/dialogs/del-lugar-dialog/del-lugar-dialog.component';
import { UpdLugarDialogComponent } from '../../components/dialogs/upd-lugar-dialog/upd-lugar-dialog.component';
import { EstadoService } from '@core/services/estado/estado.service';
import { PaisService } from '@core/services/pais/pais.service';
import { MunicipioService } from '@core/services/municipio/municipio.service';
import { ParroquiaService } from '@core/services/parroquia/parroquia.service';
import { Estado } from '@models/estado';
import { Pais } from '@models/pais';
import { Municipio } from '@models/municipio';
import { Parroquia } from '@core/models/parroquia';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

interface SearchLug {
  t: string;
  do: number;
}

@Component({
  selector: 'app-lugares',
  templateUrl: './lugares.component.html',
  styleUrls: ['./lugares.component.css'],
})
export class LugaresComponent implements OnInit {
  constructor(
    private formBuilder: FormBuilder,
    private _paisService: PaisService,
    private _estadoService: EstadoService,
    private _municipioService: MunicipioService,
    private _parroquiaService: ParroquiaService
  ) {}
  userSelection: number = 0;

  op: string = '';
  searchState: string = 'U';
  tipoLugar: string = ''; //PR,MU,ES,PA
  opStatus: string; //S,P,D
  showRes:number = 0;
  targetData: any;
  searchForm: FormGroup;
  addForm: FormGroup;
  _paises: Pais[] = [];
  _estados: Estado[] = [];
  _municipios: Municipio[] = [];
  _parroquias: Parroquia[] = [];

  //CONTROL DE BUSQUEDA
  searchLugar: SearchLug[] = [];
  searchResults = {
    PA: [],
    ES: [],
    MU: [],
    PR: [],
  };
  async setData(inD) {
    this.targetData = inD;
  }
  @ViewChild('updLugar') private updComponent: UpdLugarDialogComponent;
  async openUpdModal(id: number, tipo: string, data: any) {
    await this.setData(data);
    return await this.updComponent.open(tipo, this.targetData);
  }
  @ViewChild('delLugar') private delComponent: DelLugarDialogComponent;
  async openDelModal(id: number, tipo: string, data: any) {
    return await this.delComponent.open(id, tipo, data);
  }
  //dataSource : MatTableDataSource<Lugar>;
  //CHEQUEO DE OPERACION
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
  getAsociados() {
    this.getPaises();
    /*this.getEstados();
    this.getMunicipios();
    this.getParroquias();*/
  }
  checkForSearch(i: number) {
    if (this.searchLugar[i].do === 1) {
      this.searchLugar[i].do = 0;
    } else {
      this.searchLugar[i].do = 1;
    }
  }
  searchCheck(pos: number) {
    if (this.searchLugar[pos].do === 1) {
      return true;
    }
    return false;
  }
  setOperation(chOp: string) {
    this.op = chOp;
    if (chOp !== '') {
      this.searchState = 'I';
      this.opStatus = 'S';
      this.setTipoLugar('');
    } else {
      this.searchState = 'U';
    }
  }
  ngOnInit(): void {
    this.setOperation('');
    this.searchState = 'U';
    this.showRes = 0;
    this.searchLugar = [
      { t: 'PA', do: 0 },
      { t: 'ES', do: 0 },
      { t: 'MU', do: 0 },
      { t: 'PR', do: 0 },
    ];
    this.searchForm = this.formBuilder.group({
      nombre: '',
    });
    //this.getAsociados();
    this.addForm = this.formBuilder.group({
      nombre: null,
      pais: null,
      estado: null,
      municipio: null,
      valor_socio_economico: null,
    });
  }
  addPais(data) {
    this._paisService.createPais(data).subscribe(
      (response) => {
        console.log(response);
        //alert('Se agrego el pais correctamente');
        this.opStatus = 'D';
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
      }
    );
  }
  getPaises() {
    this._paisService.getPaises().subscribe(
      (response) => {
        console.log(response);
        this._paises = response.data;
        this.getEstados();
      },
      (error) => {
        console.log(error);
        this._paises = [
          this.testPais,
          {
            _id: 2,
            nombre: 'Argentina',
          },
        ];
        this.getEstados();
      }
    );
  }

  addEstado(data) {
    this._estadoService.createEstado(data).subscribe(
      (response) => {
        console.log(response);
        //alert('Se agrego el estado correctamente');
        this.opStatus = 'D';
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
      }
    );
  }

  getEstados() {
    this._estadoService.getEstados().subscribe(
      (response) => {
        console.log(response);
        this._estados = response.data;
        this.getMunicipios();
      },
      (error) => {
        console.log(error);
        this._estados = [this.testEstado];
        this.getMunicipios();
      }
    );
  }

  getMunicipios() {
    this._municipioService.getMunicipios().subscribe(
      (response) => {
        console.log(response);
        this._municipios = response.data;
        this.getParroquias();
      },
      (error) => {
        console.log(error);
        this._municipios = [this.testMunicipio];
        this.getParroquias();
      }
    );
  }

  getParroquias() {
    this._parroquiaService.getParroquias().subscribe(
      (response) => {
        console.log(response);
        this._parroquias = response.data;
        this.setSearchResults();
        this.searchState = 'D';
      },
      (error) => {
        console.log(error);
        this._parroquias = [this.testParroquia];
        this.setSearchResults();
        this.searchState = 'D';
      }
    );
  }

  addParroquia(data) {
    this._parroquiaService.createParroquia(data).subscribe(
      (response) => {
        console.log(response);
        //alert('Se agrego la parroquia correctamente');
        this.opStatus = 'D';
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
      }
    );
  }

  addMunicipio(data) {
    this._municipioService.createMunicipio(data).subscribe(
      (response) => {
        console.log(response);
        //alert('Se agrego el municipio correctamente');
        this.opStatus = 'D';
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
      }
    );
  }

  dataFilter(dataArray) {
    //console.log(this.searchForm.value);
    let filtered = [];
    dataArray.forEach((res, ind) => {
      let inc = true;
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
    console.log(dataArray, filtered);
    return filtered;
  }
  setSearchResults() {
    this.searchResults = {
      PA: this.searchLugar[0].do === 1 ? this.dataFilter(this._paises) : [],
      ES: this.searchLugar[1].do === 1 ? this.dataFilter(this._estados) : [],
      MU: this.searchLugar[2].do === 1 ? this.dataFilter(this._municipios) : [],
      PR: this.searchLugar[3].do === 1 ? this.dataFilter(this._parroquias) : [],
    };
    console.log(this.searchResults);
  }
  invokeSearch() {
    //console.log(this.searchLugar, this.searchForm.value);
    console.log('Doing search');
    this.searchState = 'P';
    this.getAsociados();
  }
  selectUser(id: number) {
    if (id === this.userSelection) {
      this.userSelection = 0;
    } else {
      this.userSelection = id;
    }
  }
  isSelected(id: number): boolean {
    if (id === this.userSelection) {
      return true;
    }
    return false;
  }
  doSearch() {
    this.searchState = 'I';
    this.searchLugar = [
      { t: 'PA', do: 0 },
      { t: 'ES', do: 0 },
      { t: 'MU', do: 0 },
      { t: 'PR', do: 0 },
    ];
  }
  setTipoLugar(tipo: string) {
    this.tipoLugar = tipo;
  }
  serviceInvoke(role: string) {
    //console.log(this.addForm.value);
    let values = this.addForm.value;
    let toCreate: any = {};
    this.opStatus = 'P';
    switch (role) {
      case 'PA':
        toCreate.nombre = values.nombre;
        this.addPais(toCreate);
        break;
      case 'ES':
        toCreate.nombre = values.nombre;
        toCreate.fk_pais = values.pais;
        console.log(toCreate);
        this.addEstado(toCreate);
        break;
      case 'MU':
        toCreate.nombre = values.nombre;
        toCreate.fk_estado = values.estado._id;
        console.log(toCreate);
        this.addMunicipio(toCreate);
        break;
      case 'PR':
        toCreate.nombre = values.nombre;
        toCreate.fk_municipio = values.municipio._id;
        toCreate.valor_socio_economico = values.valor_socio_economico;
        console.log(toCreate);
        this.addParroquia(toCreate);
        break;
      default:
        break;
    }
    //this.getAsociados();
  }
}
