import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MatTableDataSource } from '@angular/material/table';
import { SubCategoria } from '@models/subcategoria';
import { Categoria } from '@models/categoria';
import { CategoriaService } from '@core/services/categoria/categoria.service';
import { SubcategoriaService } from '@core/services/subcategoria/subcategoria.service';
import { UpdSubCategoriaDialogComponent } from '../../components/dialogs/upd-sub-catategoria-dialog/upd-sub-catategoria-dialog.component';
import { DelSubCategoriaDialogComponent } from '../../components/dialogs/del-sub-catategoria-dialog/del-sub-catategoria-dialog.component';

@Component({
  selector: 'app-subcategoria',
  templateUrl: './subcategoria.component.html',
  styleUrls: ['./subcategoria.component.css'],
})
export class SubcategoriaComponent implements OnInit {
  op: string;
  searchState: string;
  opStatus: string; //S,P,D
  userSelection: number = 0;
  categorias: Categoria[] = [];
  subcategorias: SubCategoria[] = [];
  dataSource: MatTableDataSource<SubCategoria>;

  searchForm: FormGroup;
  addForm: FormGroup;

  targetData: any;

  displayedColumns: string[] = ['id', 'desc', 'selector', 'ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();

  setOperation(chOp: string) {
    this.op = chOp;
    if (chOp !== '') {
      this.searchState = 'I';
      this.opStatus = 'S';
    } else {
      this.searchState = 'U';
    }
  }

  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _subcategoriaService: SubcategoriaService,
    private _categoriaService: CategoriaService
  ) {}

  @ViewChild('updSubCat') private updComponent: UpdSubCategoriaDialogComponent;
  async openUpdModal() {
    return await this.updComponent.open();
  }
  @ViewChild('delSubCat') private delComponent: DelSubCategoriaDialogComponent;
  async openDelModal() {
    return await this.delComponent.open();
  }

  ngOnInit(): void {
    this.setOperation('');
    this.searchState = 'U';
    this.searchForm = this.formBuilder.group({
      nombre: null,
      activo: null,
      categoria: null,
      creado_el: null,
    });
    this.addForm = this.formBuilder.group({
      nombre: null,
      categoria: null,
    });
    this.getSubCategorias();
    this.getCategorias();
    //this.getAsociados();
  }
  getCategorias() {
    this._categoriaService.getCategorias().subscribe(
      (response) => {
        console.log(response);
        this.categorias = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }
  getSubCategorias() {
    this._subcategoriaService.getSubCategorias().subscribe(
      (response) => {
        console.log(response);
        this.subcategorias = response.data;
        this.opStatus = 'D';
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
      }
    );
  }
  updateSubCategoria(id, data) {
    this._subcategoriaService.updateSubCategoria(id, data).subscribe(
      (response) => {
        console.log(response);
        //alert('Se ha modificado correctamente');
      },
      (error) => {
        console.log(error.error.message);
      }
    );
  }
  deleteSubCategoria(id, data) {
    this._subcategoriaService.deleteSubCategoria(id, data).subscribe(
      (response) => {
        console.log(response);
        //alert('Se ha modificado correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }
  addSubCategoria(data) {
    this._subcategoriaService.createSubCategoria(data).subscribe(
      (response) => {
        console.log(response);
        //alert('Se ha agregado correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }
  selectUser(id: number, data) {
    if (id === this.userSelection) {
      this.userSelection = 0;
      this.targetData = null;
    } else {
      this.userSelection = id;
      this.targetData = data;
    }
  }
  isSelected(id: number): boolean {
    if (id === this.userSelection) {
      return true;
    }
    return false;
  }
  dataFilter(dataArray: SubCategoria[]): SubCategoria[] {
    //console.log(this.searchForm.value);
    let filtered: SubCategoria[] = [];
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
          } else if (
            typeof field === 'string' &&
            res[key].toLowerCase().startsWith(field.toLowerCase())
          ) {
            return;
          } else if (typeof field === 'boolean' && res[key] === field) {
            return;
          } else if (
            typeof field === 'object' &&
            res[key]._id === field['_id']
          ) {
            console.log('Object validation');
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
    //console.log(dataArray, filtered);
    return filtered;
  }
  invokeService() {
    console.log(this.addForm.value);
    let values: any = this.addForm.value;
    let toAdd: any = {};
    toAdd.nombre = values.nombre;
    toAdd.categoria = values.categoria;
    this.addSubCategoria(toAdd);
    //this.getAsociados();
    this.getSubCategorias();
  }
  async invokeSearch() {
    this.subcategorias = [];
    this.userSelection = 0;
    if (this.searchForm.value['creado_el'] !== null) {
      this.searchForm
        .get('creado_el')
        .setValue(new Date(this.searchForm.value['creado_el']));
    }
    this.searchState = 'P';

    this._subcategoriaService.getSubCategorias().subscribe(
      (response) => {
        console.log(response);
        this.subcategorias = response.data;
        this.dataSource = new MatTableDataSource<SubCategoria>(
          this.dataFilter(this.subcategorias)
        );
        this.searchState = 'D';
      },
      (error) => {
        console.log(error);
        this.subcategorias = [
          {
            _id: 1,
            nombre: 'test subcategoria',
            categoria: { _id: 1, nombre: 'test categoria' },
          },
        ];
        this.dataSource = new MatTableDataSource<SubCategoria>(
          this.dataFilter(this.subcategorias)
        );
        this.searchState = 'D';
      }
    );
  }
  doSearch() {
    this.searchState = 'I';
  }
  /*getAsociados() {
    this._categoriaService.getCategorias().subscribe(
      (response) => {
        console.log(response);
        this.categorias = response.data;
        this.opStatus = 'D';
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
        this.categorias = [{ _id: 1, nombre: 'test categoria' }];
      }
    );
  }*/
}
