import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './components/login/login.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import {RegistroComponent} from "./components/registro/registro.component";
import {RegistroDatoEncuestadoComponent} from "./components/registro-dato-encuestado/registro-dato-encuestado.component";
import {AgregarTelefonoComponent} from "./components/agregar-telefono/agregar-telefono.component";
import { AddClienteDialogComponent } from './components/add-cliente-dialog/add-cliente-dialog.component';
import { MaterialModule } from '../material.module';

@NgModule({
  declarations: [
    LoginComponent,
    ResetPasswordComponent,
    ChangePasswordComponent,
    RegistroComponent,
    RegistroDatoEncuestadoComponent,
    AgregarTelefonoComponent,
    AddClienteDialogComponent,
  ],
  imports: [CommonModule, FormsModule, AuthRoutingModule, ReactiveFormsModule, MaterialModule],
})
export class AuthModule {}
