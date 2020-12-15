import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MatTableDataSource } from '@angular/material/table';
import { Tipo } from '@models/tipo';
import { TipoService } from '@core/services/tipo/tipo.service';
@Component({
  selector: 'app-tipos',
  templateUrl: './tipos.component.html',
  styleUrls: ['./tipos.component.css'],
})
export class TiposComponent implements OnInit {
  op: string;
  searchState: string;
  opStatus: string; //S,P,D
  userSelection: number = 0;

  tipos: Tipo[] = [];
  dataSource: MatTableDataSource<Tipo>;

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
    private _tipoService: TipoService
  ) {}
  /*
  @ViewChild('updPresentacion') private updComponent:UpdPresentacionDialogComponent;
  async openUpdModal() {
    return await this.updComponent.open();
  }
  @ViewChild('delPresentacion') private delComponent:DelPresentacionDialogComponent;
  async openDelModal() {
    return await this.delComponent.open();
  }*/

  ngOnInit(): void {
    this.setOperation('');
    this.searchState = 'U';
    this.searchForm = this.formBuilder.group({
      nombre: null,
      creado_el: null,
    });
    this.getTipos();
  }

  getTipos() {
    this._tipoService.getTipos().subscribe(
      (response) => {
        console.log(response);
        this.tipos = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getTipo(id) {
    this._tipoService.getTipo(id).subscribe(
      (response) => {
        console.log(response);
        // Obtener el tipo con response.data
      },
      (error) => {
        console.log(error);
      }
    );
  }

  addTipo(data) {
    this._tipoService.createTipo(data).subscribe(
      (response) => {
        console.log(response);
        alert('Se agrego el Tipo correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }

  modifyTipo(id, data) {
    this._tipoService.updateTipo(id, data).subscribe(
      (response) => {
        console.log(response);
        alert('Se modifico el Tipo correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }

  deleteTipo(id, data) {
    this._tipoService.deleteTipo(id, data).subscribe(
      (response) => {
        console.log(response);
        alert('Se elimino el Tipo correctamente');
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
  /*dataFilter(dataArray:Presentacion[]): Presentacion[]{
    console.log(this.searchForm.value);
    let filtered: Presentacion[] = [];
    dataArray.forEach((res,ind) => {
      let inc = true;
      Object.entries(this.searchForm.value).forEach(([key,field],_ind)=>{
        if(inc === true && field !== null){
          if(field instanceof Date && (res[key] >= field && res[key] <= Date.now())){
            return;
          }
          else if(typeof(field)==='string' && res[key].startsWith(field)){
            return;
          }
          else if(typeof(field)==='boolean' && res[key]===field){
            return;
          }
          else{
            inc = false;
          }
        }
      })
      if(inc === true){
        filtered.push(res);
      }
    })
    console.log(dataArray,filtered);
    return filtered;
  }*/
  invokeSearch() {
    /* this.presentaciones = [];
    this.userSelection=0;
    if(this.searchForm.value['creado_el'] !== null){
      this.searchForm.get('creado_el').setValue(new Date(this.searchForm.value['creado_el']));
    }
    this.searchState="P";
    setTimeout(()=>{
      //DATA SOURCE EDIT
      this.dataSource = new MatTableDataSource<Presentacion>(this.dataFilter(this.presentaciones));
      this.searchState="D";
    },3000);*/
  }
  doSearch() {
    this.searchState = 'I';
  }
}
