import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { PresentacionService } from '@core/services/presentacion/presentacion.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MatTableDataSource } from '@angular/material/table';
import { Presentacion } from '@core/models/presentacion';
import { DelPresentacionDialogComponent } from '../../components/dialogs/del-presentacion-dialog/del-presentacion-dialog.component';
import { UpdPresentacionDialogComponent } from '../../components/dialogs/upd-presentacion-dialog/upd-presentacion-dialog.component';

@Component({
  selector: 'app-presentacion',
  templateUrl: './presentacion.component.html',
  styleUrls: ['./presentacion.component.css'],
})
export class PresentacionComponent implements OnInit {
  op: string;
  searchState: string;
  opStatus: string; //S,P,D
  userSelection: number = 0;

  presentaciones: Presentacion[] = [];
  dataSource: MatTableDataSource<Presentacion>;

  searchForm: FormGroup;
  addForm: FormGroup;

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
    private _presentacionService: PresentacionService
  ) {}

  @ViewChild('updPresentacion')
  private updComponent: UpdPresentacionDialogComponent;
  async openUpdModal() {
    return await this.updComponent.open();
  }
  @ViewChild('delPresentacion')
  private delComponent: DelPresentacionDialogComponent;
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
  }

  getPresentaciones() {
    this._presentacionService.getPresentaciones().subscribe(
      (response) => {
        console.log(response);
        this.presentaciones = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  addPresentacion(data) {
    this._presentacionService.createPresentacion(data).subscribe(
      (response) => {
        console.log(response);
        alert('Se agrego la presentacion correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }
  updatePresentacion(id, data) {
    this._presentacionService.updatePresentacion(id, data).subscribe(
      (response) => {
        console.log(response);
        alert('Se modifico la presentacion correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
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
  dataFilter(dataArray: Presentacion[]): Presentacion[] {
    //console.log(this.searchForm.value);
    let filtered: Presentacion[] = [];
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
  invokeSearch() {
    //this.presentaciones = [];
    //this.userSelection = 0;
    let toAdd: any = {};
    //Campos que se deben enviar
    //toAdd.cantidad
    //toAdd.tipo
    this.addPresentacion(toAdd);
    if (this.searchForm.value['creado_el'] !== null) {
      this.searchForm
        .get('creado_el')
        .setValue(new Date(this.searchForm.value['creado_el']));
    }
    this.searchState = 'P';
    setTimeout(() => {
      //DATA SOURCE EDIT
      this.dataSource = new MatTableDataSource<Presentacion>(
        this.dataFilter(this.presentaciones)
      );
      this.searchState = 'D';
    }, 3000);
  }
  doSearch() {
    this.searchState = 'I';
  }
}
