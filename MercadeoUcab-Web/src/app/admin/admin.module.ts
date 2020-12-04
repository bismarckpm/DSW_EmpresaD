import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { EstudiosComponent } from './pages/estudios/estudios.component';
import { CategoriasComponent } from './pages/categorias/categorias.component';
import { UsuariosComponent } from './pages/usuarios/usuarios.component';
import { PresentacionComponent } from './pages/presentacion/presentacion.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { DataTableComponent } from './components/data-table/data-table.component';
import { MaterialModule } from '../material.module';
import { BaseDialogComponent } from './components/base-dialog/base-dialog.component';
import { UserInfoDialogComponent } from './components/user-info-dialog/user-info-dialog.component';
import { LocalNgbModule } from '../ngbootstrap.module';
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import { AddUserDialogComponent } from './components/dialogs/add-user-dialog/add-user-dialog.component';
import { UpdateUserDialogComponent } from './components/dialogs/update-user-dialog/update-user-dialog.component';
import { DeleteUserDialogComponent } from './components/dialogs/delete-user-dialog/delete-user-dialog.component';
import { AddTipoDialogComponent } from './components/dialogs/add-tipo-dialog/add-tipo-dialog.component';
import { UpdateTipoDialogComponent } from './components/dialogs/update-tipo-dialog/update-tipo-dialog.component';
import { DeleteTipoDialogComponent } from './components/dialogs/delete-tipo-dialog/delete-tipo-dialog.component';
import { AddPreguntaDialogComponent } from './components/dialogs/add-pregunta-dialog/add-pregunta-dialog.component';
import { UpdatePreguntaDialogComponent } from './components/dialogs/update-pregunta-dialog/update-pregunta-dialog.component';

@NgModule({
  declarations: [
    EstudiosComponent, 
    CategoriasComponent, 
    UsuariosComponent, 
    PresentacionComponent,
    DashboardComponent, 
    DataTableComponent, 
    BaseDialogComponent, UserInfoDialogComponent, AddUserDialogComponent, UpdateUserDialogComponent, DeleteUserDialogComponent, AddTipoDialogComponent, UpdateTipoDialogComponent, DeleteTipoDialogComponent, AddPreguntaDialogComponent, UpdatePreguntaDialogComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MaterialModule,
    LocalNgbModule,
    FormsModule,ReactiveFormsModule
  ]
})
export class AdminModule { }
