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


@NgModule({
  declarations: [EstudiosComponent, CategoriasComponent, UsuariosComponent, PresentacionComponent, DashboardComponent, DataTableComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MaterialModule
  ]
})
export class AdminModule { }
