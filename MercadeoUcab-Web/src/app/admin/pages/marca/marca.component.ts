import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MatTableDataSource } from '@angular/material/table';
import { Marca } from '@models/marca';
import { MarcaService } from '@core/services/marca/marca.service';
import { DelMarcaDialogComponent } from '../../components/dialogs/del-marca-dialog/del-marca-dialog.component';
import { UpdMarcaDialogComponent } from '../../components/dialogs/upd-marca-dialog/upd-marca-dialog.component';

@Component({
  selector: 'app-marca',
  templateUrl: './marca.component.html',
  styleUrls: ['./marca.component.css'],
})
export class MarcaComponent implements OnInit {
  op: string;
  searchState: string;
  opStatus: string; //S,P,D,E
  userSelection: number = 0;
  marcas: Marca[] = [];
  dataSource: MatTableDataSource<Marca>;
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
    private _marcaService: MarcaService
  ) {}

  @ViewChild('updMarca') private updComponent: UpdMarcaDialogComponent;
  async openUpdModal() {
    return await this.updComponent.open();
  }
  @ViewChild('delMarca') private delComponent: DelMarcaDialogComponent;
  async openDelModal() {
    return await this.delComponent.open();
  }

  ngOnInit(): void {
    this.setOperation('');
    this.searchState = 'U';
    this.searchForm = this.formBuilder.group({
      nombre: null,
      creado_el: null,
    });
    this.addForm = this.formBuilder.group({
      nombre: null,
    });
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
  getMarcas() {
    this._marcaService.getMarcas().subscribe(
      (response) => {
        console.log(response);
        this.marcas = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }
  addMarca(data) {
    this._marcaService.createMarca(data).subscribe(
      (response) => {
        console.log(response);
        alert('Se agrego la marca correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }
  updateMarca(id, data) {
    this._marcaService.updateMarca(id, data).subscribe(
      (response) => {
        console.log(response);
        alert('Se modifico la marca correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }

  deleteMarca(id, data) {
    this._marcaService.deleteMarca(id, data).subscribe(
      (response) => {
        console.log(response);
        alert('Se elimino la marca correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }
  isSelected(id: number): boolean {
    if (id === this.userSelection) {
      return true;
    }
    return false;
  }
  dataFilter(dataArray: Marca[]): Marca[] {
    console.log(this.searchForm.value);
    let filtered: Marca[] = [];
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
    console.log(dataArray, filtered);
    return filtered;
  }
  invokeService() {
    console.log(this.addForm.value);
    this.opStatus = 'P';
    this._marcaService.createMarca(this.addForm.value).subscribe(
      (response) => {
        console.log(response);
        this.opStatus = 'D';
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
      }
    );
  }
  async invokeSearch() {
    this.marcas = [];
    this.userSelection = 0;
    if (this.searchForm.value['creado_el'] !== null) {
      this.searchForm
        .get('creado_el')
        .setValue(new Date(this.searchForm.value['creado_el']));
    }
    this.searchState = 'P';

    this._marcaService.getMarcas().subscribe(
      (response) => {
        console.log(response);
        this.marcas = response.data;
        this.dataSource = new MatTableDataSource<Marca>(
          this.dataFilter(this.marcas)
        );
        this.searchState = 'D';
      },
      (error) => {
        console.log(error);
        this.marcas = [
          {
            _id: 1,
            nombre: 'test marca',
          },
        ];
        this.dataSource = new MatTableDataSource<Marca>(
          this.dataFilter(this.marcas)
        );
        this.searchState = 'D';
      }
    );
  }
  doSearch() {
    this.searchState = 'I';
  }
}
