import {
  Component,
  Input,
  Injectable,
  OnInit,
  TemplateRef,
  ViewChild,
  EventEmitter,
  Output,
} from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup } from '@angular/forms';
import { PreguntaService } from '@core/services/pregunta/pregunta.service';

interface OptionItem {
  nombre_opcion: string;
}

@Component({
  selector: 'app-add-pregunta-dialog',
  templateUrl: './add-pregunta-dialog.component.html',
  styleUrls: ['./add-pregunta-dialog.component.css'],
})
@Injectable()
export class AddPreguntaDialogComponent implements OnInit {
  opStatus: string;
  addForm: FormGroup;
  optionList: OptionItem[] = [];
  minF: number = 0;
  maxF: number = 0;
  sentPreg: any = null;

  @ViewChild('addPreg')
  private modalContent: TemplateRef<AddPreguntaDialogComponent>;
  private modalRef: NgbModalRef;
  //@Input() sharePregunta: (newPreg: any) => void;
  @Output('sharePregunta')
  sharePregunta: EventEmitter<any> = new EventEmitter<any>();

  pregTipos: any[] = [
    { name: 'Abierta', t: 'abierta' },
    { name: 'Selección simple', t: 'simple' },
    { name: 'Selección múltiple', t: 'multiple' },
    { name: 'Verdadero o Falso', t: 'boolean' },
    { name: 'Valor dentro de rango', t: 'rango' },
  ];

  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _preguntaService: PreguntaService
  ) {}

  rangeConcat(limit, val) {
    if (limit === 0) {
      this.minF = val;
    } else if (limit === 1) {
      this.maxF = val;
    }
    if (this.minF !== 0 && this.maxF !== 0 && this.minF < this.maxF) {
      this.addForm.get('rango').setValue(`${this.minF}&${this.maxF}`);
    }
  }

  setOption(opInd, opName) {
    this.optionList[opInd].nombre_opcion = opName;
  }

  addOption() {
    this.optionList.push({
      nombre_opcion: 'Nueva opción',
    });
    console.log(this.optionList);
  }

  resizeOptionList(opInd) {
    this.optionList = this.optionList
      .map((op, ind) => {
        if (ind !== opInd) {
          return op;
        } else {
          return undefined;
        }
      })
      .filter((el) => el !== undefined);
  }

  ngOnInit(): void {
    this.opStatus = 'S';
    this.addForm = this.formBuilder.group({
      nombre_pregunta: null,
      tipo: null,
      rango: null,
      fk_usuario: null,
      opciones: null,
    });
  }

  open() {
    this.modalRef = this.modalService.open(this.modalContent);
    this.modalRef.result.then();
  }
  close() {
    this.opStatus = 'S';
    this.modalRef.close();
    this.addForm = this.formBuilder.group({
      nombre_pregunta: null,
      tipo: null,
      rango: null,
      //EXTRAER ID DE USUARIO
      fk_usuario: 1,
      opciones: null,
    });
  }
  addPregunta(data) {
    console.log(data);
    this._preguntaService.createPregunta(data).subscribe(
      (response: any) => {
        console.log(response);
        this.opStatus = 'D';
        this.sharePregunta.emit({
          _id: response._id,
          pregunta: {
            _id: response._id,
            nombre: data.nombre_pregunta,
            tipo: data.tipo,
          },
        });
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
        this.sharePregunta.emit({
          _id: 101,
          pregunta: {
            _id: 101,
            pregunta: data.nombre_pregunta,
            tipo: data.tipo,
          },
        });
      }
    );
  }
  invokeService() {
    if (
      (this.addForm.get('tipo').value === 'simple' ||
        this.addForm.get('tipo').value === 'multiple') &&
      this.optionList.length > 0
    ) {
      this.addForm.get('opciones').setValue(this.optionList);
      console.log('');
    }
    this.opStatus = 'P';
    let toAdd: any = {};
    let values = this.addForm.value;
    toAdd.nombre_pregunta = values.nombre_pregunta;
    toAdd.tipo = values.tipo;
    toAdd.rango = values.rango;
    toAdd.opciones = values.opciones;
    //toAdd.usuarioDto = values.fk_usuario;
    // CAMBIAR A ID DEL ADMIN LOGEADO
    toAdd.usuarioDto = 1;
    console.log('PRUEBA');
    this.addPregunta(toAdd);
    this.addForm = this.formBuilder.group({
      nombre_pregunta: null,
      tipo: null,
      rango: null,
      fk_usuario: 1,
      opciones: null,
    });
    this.optionList = [];
  }
}
