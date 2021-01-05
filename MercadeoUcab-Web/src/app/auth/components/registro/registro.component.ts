import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsuarioService } from '@core/services/usuario/usuario.service';
import { Router } from '@angular/router';
import { Usuario } from '@models/usuario';
import { UpdateSolicitudDialogComponent } from '../../../cliente/components/dialogs/upd-solicitud-dialog/update-solicitud-dialog.component';
import { RegistroDatoEncuestadoComponent } from '../registro-dato-encuestado/registro-dato-encuestado.component';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css'],
})
export class RegistroComponent implements OnInit {
  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private usuarioService: UsuarioService
  ) {
    this.addForm = this.formBuilder.group({
      nombre: null,
      password: null,
      apellido: null,
      rol: 'encuestado',
      estado: 'activo',
      correo: null,
    });
  }
  op: string;
  usuarios: Usuario[] = [];
  searchState: string; // U.I,D
  toSearch2: any = {};
  addForm: FormGroup;
  opStatus: string; // S,P,D
  IDusuario: number;
  @ViewChild('registroDatoEncuestado')
  private registroDatoEncuestado: RegistroDatoEncuestadoComponent;

  addUsuarios(data) {
    this.usuarioService.createUser(data).subscribe(
      (response: any) => {
        console.log(response);
        if (response.status === 200) {
          // Se hace lo que se quiera en exito
          alert(response.message);
          this.IDusuario = response._id;
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

  ngOnInit(): void {}
  async openDatoEncuestadoModal() {
    if (this.addForm.valid) {
      return await this.registroDatoEncuestado.open();
    } else {
      alert(
        'Se equivoco a la hora de registrar los campos(nombre, apellido, correo y password son campos obligatorios no pueden estar vacios)'
      );
    }
  }

  navegacionLogin() {
    this.router.navigate(['/registrar/datoUsuario']);
  }

  get nombre() {
    return this.addForm.get('nombre');
  }

  serviceInvoke() {
    if (this.addForm.valid) {
      console.log(this.addForm.valid);
      const toAdd = {
        nombre: null,
        password: null,
        apellido: null,
        rol: 'encuestado',
        estado: 'activo',
        correo: null,
      };
      const values = this.addForm.value;
      toAdd.nombre = values.nombre;
      toAdd.apellido = values.apellido;
      toAdd.password = values.password;
      toAdd.rol = 'encuestado';
      toAdd.estado = 'activo';
      toAdd.correo = values.correo;
      console.log(toAdd);
      this.addUsuarios(toAdd);
      this.opStatus = 'P';
      setTimeout(() => {
        this.addForm = this.formBuilder.group({
          nombre: null,
          password: null,
          apellido: null,
          rol: 'encuestado',
          estado: 'activo',
          correo: null,
        });
        this.opStatus = 'D';
      }, 3000);
    }
  }
}
