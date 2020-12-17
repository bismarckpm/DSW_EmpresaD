import { Component, OnInit, ViewChild } from '@angular/core';
import { Solicitud } from '@models/solicitud';
import { MatTableDataSource } from '@angular/material/table';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SolicitudService } from '@core/services/solicitud/solicitud.service';
import { MarcaService } from '@core/services/marca/marca.service';
import {SubcategoriaService} from '@core/services/subcategoria/subcategoria.service';
import {PresentacionService} from '@core/services/presentacion/presentacion.service';
import {TipoService} from '@core/services/tipo/tipo.service';
import { UpdateSolicitudDialogComponent } from '../../../cliente/components/dialogs/upd-solicitud-dialog/update-solicitud-dialog.component';
import { DeleteUserDialogComponent } from '../../../admin/components/dialogs/delete-user-dialog/delete-user-dialog.component';
import { Usuario } from '@models/usuario';
import { Marca } from '@models/marca';
import {SubCategoria} from '@models/subcategoria';
import {Presentacion} from '@models/presentacion';
import {Tipo} from '@models/tipo';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'app-Solicitud',
  templateUrl: './Solicitud.component.html',
  styleUrls: ['./Solicitud.component.css'],
})
export class SolicitudComponent implements OnInit {

  constructor(
    // private modalService: NgbModal,
    private formBuilder: FormBuilder,
    // tslint:disable-next-line:variable-name
    private _solicitudService: SolicitudService,
    private marcaServices: MarcaService,
    private subcategoriaService: SubcategoriaService,
    private  presentacionService: PresentacionService,
    private tipoService: TipoService,
  ) {
    this.addForm = this.formBuilder.group({
      marca: null,
      presentacion: null,
      usuario: null,
      tipo: null,
      subCategoria: null,
      estado: 'solicitada',
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
  }
  // CONTROL DE ESTADO DEL COMPONENTE
  op: string;
  searchState: string; // U.I,D
  toSearch2: any = {};
  solicitudes: Solicitud[] = [];
  solicitudes2: Solicitud[] = [];
  marcas: Marca[] = [];
  subcategorias: SubCategoria[] = [];
  presentaciones: Presentacion[] = [];
  tipos: Tipo[] = [];

  // COLUMNAS DE TABLA DE RESULTADOS
  displayedColumns: string[] = ['id', 'selector', 'estado' , 'creado' , 'modificado', 'usuario' , 'Marca' ,'ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();

  // INDICE DE SOLICITUD SELECCIONADO
  solicitudSelection = 0;

  // LISTA DE SOLICITUDES DEVUELTOS EN BÚSQUEDA
  dataSource: MatTableDataSource<Solicitud>;
  solicitudTarget: Solicitud;
  // FORMULARIOS
  searchForm: FormGroup;
  searchModel: Solicitud;
  addForm: FormGroup;
  opStatus: string; // S,P,D
  userSolicitud: number;
  @ViewChild('updSolicitud')
  private updComponent: UpdateSolicitudDialogComponent;
  setUsuarioSolicitud(U: number) {
    this.userSolicitud = U;
  }
  /*getTarget(id:number){
    this.users.forEach((user,ind) => {
      if(user._id === id){

      }
    });
  };*/





  getPresentacion() {
    this.presentacionService.getPresentaciones().subscribe(
      (response) => {
        console.log(response);
        this.presentaciones = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getTipos() {
    this.tipoService.getTipos().subscribe(
      (response) => {
        console.log(response);
        this.tipos = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getSubcategoria() {
    this.subcategoriaService.getSubCategorias().subscribe(
      (response) => {
        console.log(response);
        this.subcategorias = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getMarcas() {
    this.marcaServices.getMarcas().subscribe(
      (response) => {
        console.log(response.data.nombre);
        this.marcas = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  addSolicitud(data) {
    this._solicitudService.createSolicitud(data).subscribe(
      (response: any) => {
        console.log(response);
        if (response.status === 200) {
          // Se hace lo que se quiera en exito
          alert(response.message);
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

  updateSolicitud(id, data) {
    this._solicitudService.updateSolicitud(id, data).subscribe(
      (response) => {
        console.log(response);
        alert('Se modifico la solicitud correctamente');
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
        alert('Se elimino la solicitud correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }

  ngOnInit(): void {
    this.setOperation('');
    this.searchState = 'U';
    this.getMarcas();
    this.getSubcategoria();
    this.getPresentacion();
    this.getTipos();
  }
  async openUpdModal() {
    return await this.updComponent.open();
  }
  serviceInvoke() {
    /*
{
    "estado":"solicitada",
    "usuario":12,
    "marca":1,
    "tipo":1,
    "subCategoria":1,
    "presentacion":2
}    */
    // FALTA VALIDACION
    // console.log(this.addForm.value);

    const toAdd: any = {};
    const values = this.addForm.value;
    toAdd.estado = values.estado;
    toAdd.usuario = 1;
    toAdd.marca = values.marca;
    toAdd.tipo = values.tipo;
    toAdd.subCategoria = values.subCategoria;
    toAdd.presentacion = values.presentacion;
    this.addSolicitud(toAdd);
    this.opStatus = 'P';
    console.log(this.op);
    console.log(this.opStatus);
    console.log(this.addForm.get('marca').value);
    console.log(this.addForm.get('tipo').value);
    console.log(this.addForm.get('subCategoria').value);
    console.log(this.addForm.get('presentacion').value);
    setTimeout(() => {
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
    console.log(this.searchForm.value);
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
    console.log(dataArray, filtered);
    return filtered;
  }

  getSolicitudes(data) {
    this._solicitudService.getSolicitudes().subscribe(
      (response) => {
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
    toSearch.subCategoria = values.subCategoria;
    toSearch.marca = values.marca;
    toSearch.presentacion = values.presentacion;
    toSearch.creado_el = values.creado_el;
    toSearch.modificado_el = values.modificado_el;
    this.getSolicitudes(toSearch);
    this.solicitudes.forEach(function (V) {

    })

    // tslint:disable-next-line:only-arrow-functions
  }
  setOperation(chOp: string) {
    this.op = chOp;
    console.log(this.searchState);
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
