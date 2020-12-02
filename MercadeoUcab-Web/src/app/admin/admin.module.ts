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

@NgModule({
  declarations: [
    EstudiosComponent, 
    CategoriasComponent, 
    UsuariosComponent, 
    PresentacionComponent,
    DashboardComponent, 
    DataTableComponent, 
    BaseDialogComponent, UserInfoDialogComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MaterialModule,
    LocalNgbModule,
    FormsModule,ReactiveFormsModule
  ]
})
export class AdminModule { }
