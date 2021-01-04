import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { Usuario } from '@models/usuario';
import { UsuarioService } from '@core/services/usuario/usuario.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Parroquia } from '@models/parroquia';
import { ParroquiaService } from '@core/services/parroquia/parroquia.service';
import { OcupacionService } from '@core/services/ocupacion/ocupacion.service';
import { MunicipioService } from '@core/services/municipio/municipio.service';
import { EstadoService } from '@core/services/estado/estado.service';
import { PaisService } from '@core/services/pais/pais.service';
import { Municipio } from '@models/municipio';
import { Estado } from '@models/estado';
import { Pais } from '@models/pais';
import { Ocupacion } from '@models/ocupacion';
import { AgregarTelefonoComponent } from '../agregar-telefono/agregar-telefono.component';
import { DatoEncuestadoService } from '@core/services/datoEncuestado/datoEncuestado.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-registro-dato-encuestado',
  templateUrl: './registro-dato-encuestado.component.html',
  styleUrls: ['./registro-dato-encuestado.component.css'],
})
export class RegistroDatoEncuestadoComponent implements OnInit {
  data = {
    telefonos: [
      {
        telefono: '',
      },
    ],

    hijos: [
      {
        genero: '',
        edad: null,
      },
    ],
  };

  @ViewChild('registroDatoEncuestado')
  private modalContent: TemplateRef<RegistroDatoEncuestadoComponent>;
  private modalRef: NgbModalRef;
  constructor(
    private modalService: NgbModal,
    private lugarServices: ParroquiaService,
    private formBuilder: FormBuilder,
    private ocupacionServices: OcupacionService,
    private router:Router,
    private datosUsuarioService: DatoEncuestadoService
  ) {}
  cont = 0;
  parroquias: Parroquia[] = [];
  ocupaciones: Ocupacion[] = [];
  @Input() IDusuario: number;
  addForm: FormGroup;
  listaHijos: FormGroup;
  toService: any;

  /*  usuarios: Usuario[] = [];
  U: Usuario;
  lugares: Parroquia[] = [];
  municipios: Municipio[] = [];
  estados: Estado[] = [];
  paises: Pais[] = [];*/

  ngOnInit(): void {}
  open() {
    this.modalRef = this.modalService.open(this.modalContent);
    this.modalRef.result.then();
    this.listaHijos = this.formBuilder.group({
      segundoNombre: '',
      segundoapellido: '',
      cedula: '',
      medioConexion: '',
      edad: null,
      genero: '',
      nive_economico: null,
      nivelAcademico: '',
      personasHogar: null,
      fk_lugar: '',
      usuario: null,
      ocupacion: '',
      hijos: this.formBuilder.array([]),
      telefonos: this.formBuilder.array([]),
    });
    this.setTelefonos();
    this.setHijos();

    //    this.addForm = this.formBuilder.group({telefonos: this.formBuilder.array([])});
    this.getLugares();
    this.getOcupaciones();
  }
  close() {
    this.modalRef.close();
  }

  get Telefonos(): FormArray {
    return this.listaHijos.get('telefonos') as FormArray;
  }

  removerTelefono(indice: number) {
    this.Telefonos.removeAt(indice);
  }

  agregarTelefono() {
    const control = this.listaHijos.controls.telefonos as FormArray;
    control.push(
      this.formBuilder.group({
        telefono: [''],
      })
    );
  }

  addNewHijo() {
    const control = this.listaHijos.controls.hijos as FormArray;
    control.push(
      this.formBuilder.group({
        genero: [''],
        edad: [''],
      })
    );
  }

  setHijos() {
    const control = this.listaHijos.controls.hijos as FormArray;
    this.data.hijos.forEach((x) => {
      control.push(
        this.formBuilder.group({
          genero: x.genero,
          edad: JSON.stringify(x.edad),
        })
      );
    });
  }

  setTelefonos() {
    const control = this.listaHijos.controls.telefonos as FormArray;
    this.data.telefonos.forEach((x) => {
      control.push(
        this.formBuilder.group({
          telefono: x.telefono,
        })
      );
    });
  }

  addDatosUsuarios(data) {
    this.datosUsuarioService.createDatoEncuestado(data).subscribe(
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

  get Hijos(): FormArray {
    return this.listaHijos.get('hijos') as FormArray;
  }

  agregarHijos() {
    const hijosFormGroup = this.formBuilder.group({ genero: '', edad: '' });
    this.Hijos.push(this.formBuilder.control(hijosFormGroup));
  }

  removerHijos(indice: number) {
    this.Hijos.removeAt(indice);
  }

  getLugares() {
    this.lugarServices.getParroquias().subscribe(
      (response) => {
        console.log(response);
        this.parroquias = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getOcupaciones() {
    this.ocupacionServices.getOcupaciones().subscribe(
      (response) => {
        console.log(response);
        this.ocupaciones = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  serviceInvoke() {
    if (this.listaHijos.valid) {
      const toAdd2 = {
        segundoNombre: '',
        segundoapellido: '',
        cedula: '',
        medioConexion: '',
        edad: null,
        genero: '',
        nive_economico: null,
        nivelAcademico: '',
        personasHogar: null,
        fk_lugar: '',
        usuario: null,
        ocupacion: '',
        hijos: new FormArray([]),
        telefonos: new FormArray([]),
      };

      const values2 = this.listaHijos.value;
      toAdd2.segundoNombre = values2.segundoNombre;
      toAdd2.segundoapellido = values2.segundoapellido;
      toAdd2.cedula = values2.cedula;
      toAdd2.medioConexion = values2.medioConexion;
      toAdd2.edad = values2.edad;
      toAdd2.genero = values2.genero;
      toAdd2.nive_economico = values2.nive_economico;
      toAdd2.nivelAcademico = values2.nivelAcademico;
      toAdd2.personasHogar = values2.personasHogar;
      toAdd2.fk_lugar = values2.fk_lugar;
      toAdd2.usuario = this.IDusuario;
      toAdd2.ocupacion = values2.ocupacion;
      toAdd2.hijos = values2.hijos;
      toAdd2.telefonos = values2.telefonos;
      console.log(toAdd2);
      this.addDatosUsuarios(toAdd2);
      this.router.navigate(['login']);
      this.close();
    } else {
      alert(
        'Se equivoco a la hora de registrar los campos(cedula, medio de conexion, edad, genero, nivel economico, nivel academico,cantidad de personas en el hogar, lugar de residencia y Ocupacion son campos obligatorios no pueden estar vacios)'
      );
    }
  }
}
