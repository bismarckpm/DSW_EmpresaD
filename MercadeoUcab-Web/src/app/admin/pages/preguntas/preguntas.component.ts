import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Pregunta } from '@core/models/pregunta';
import { PreguntaService } from '@core/services/pregunta/pregunta.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AddPreguntaDialogComponent } from '../../components/dialogs/add-pregunta-dialog/add-pregunta-dialog.component';
import { DelPreguntaDialogComponent } from '../../components/dialogs/del-pregunta-dialog/del-pregunta-dialog.component';
import { UpdatePreguntaDialogComponent } from '../../components/dialogs/update-pregunta-dialog/update-pregunta-dialog.component';

@Component({
  selector: 'app-preguntas',
  templateUrl: './preguntas.component.html',
  styleUrls: ['./preguntas.component.css'],
})
export class PreguntasComponent implements OnInit {
  constructor(
    private formBuilder: FormBuilder,
    private _preguntaService: PreguntaService
  ) {}

  op: string;
  searchState: string;
  opStatus: string; //S,P,D,E
  targetPregunta: Pregunta;
  //INDICE DE USUARIO SELECCIONADO
  userSelection: number = 0;
  searchForm: FormGroup;
  addForm: FormGroup;
  //LISTA DE USUARIOS DEVUELTOS EN BÚSQUEDA
  preguntas: Pregunta[] = [];
  dataSource: MatTableDataSource<Pregunta>;

  displayedColumns: string[] = ['id', 'desc', 'selector', 'ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();

  setOperation(chOp: string) {
    this.op = chOp;
    if (chOp !== '') {
      this.searchState = 'I';
    } else {
      this.searchState = 'U';
      this.opStatus = 'S';
    }
  }
  @ViewChild('delPregunta') private delComponent: DelPreguntaDialogComponent;
  async openDelModal() {
    return await this.delComponent.open();
  }
  @ViewChild('updPregunta') private updComponent: UpdatePreguntaDialogComponent;
  async openUpdModal() {
    return await this.updComponent.open();
  }

  ngOnInit(): void {
    this.setOperation('');
    this.searchState = 'U';
    this.opStatus = 'S';
    this.searchForm = this.formBuilder.group({
      tipo: null,
      creado_el: null,
    });
  }
  selectUser(id: number, data: Pregunta) {
    if (id === this.userSelection) {
      this.userSelection = 0;
      this.targetPregunta = null;
    } else {
      this.userSelection = id;
      this.targetPregunta = data;
    }
  }
  isSelected(id: number): boolean {
    if (id === this.userSelection) {
      return true;
    }
    return false;
  }
  addPregunta(data) {
    this._preguntaService.createPregunta(data).subscribe(
      (response: any) => {
        console.log(response);
        alert(response.message);
        this.opStatus = 'D';
      },
      (error) => {
        console.log(error);
        alert(error.error.message);
        this.opStatus = 'E';
      }
    );
  }

  getPreguntas() {
    this._preguntaService.getPreguntas().subscribe(
      (response) => {
        console.log(response);
        this.preguntas = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  updatePregunta(id, data) {
    this._preguntaService.updatePregunta(id, data).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  deletePregunta(id, data) {
    this._preguntaService.deletePregunta(id, data).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }
  serviceInvoke() {
    this.opStatus = 'P';
    let toAdd: any = {};
    toAdd.nombre_pregunta;
    // Campos que se deben pasar al Back
    //toAdd.tipo;
    //toAdd.rango;
    //toAdd.fk_usuario;
    //toAdd.opciones;
    this.addPregunta(toAdd);

    this.addForm = this.formBuilder.group({
      nombre: null,
    });
  }
  invokeSearch() {
    this.preguntas = [];
    this.userSelection = 0;
    if (this.searchForm.value['creado_el'] !== null) {
      this.searchForm
        .get('creado_el')
        .setValue(new Date(this.searchForm.value['creado_el']));
    }
    this.searchState = 'P';
    setTimeout(() => {
      for (let i = 0; i < Math.floor(Math.random() * (100 - 1) + 1); i++) {
        /*this.preguntas.push({
         _id:Math.floor(Math.random()*(1000-1)+1),
         nombre_pregunta:Math.random().toString(36).substr(2, 10),
         tipo:'',
         rango:''
        });*/
      }
      this.dataSource = new MatTableDataSource<Pregunta>(
        this.dataFilter(this.preguntas)
      );
      this.searchState = 'D';
    }, 3000);
  }
  dataFilter(dataArray: Pregunta[]): Pregunta[] {
    console.log(this.searchForm.value);
    let filtered: Pregunta[] = [];
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
  doSearch() {
    this.searchState = 'I';
  }
}
