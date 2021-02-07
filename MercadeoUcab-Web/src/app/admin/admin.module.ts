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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddUserDialogComponent } from './components/dialogs/add-user-dialog/add-user-dialog.component';
import { UpdateUserDialogComponent } from './components/dialogs/update-user-dialog/update-user-dialog.component';
import { DeleteUserDialogComponent } from './components/dialogs/delete-user-dialog/delete-user-dialog.component';
import { AddTipoDialogComponent } from './components/dialogs/add-tipo-dialog/add-tipo-dialog.component';
import { UpdateTipoDialogComponent } from './components/dialogs/update-tipo-dialog/update-tipo-dialog.component';
import { DeleteTipoDialogComponent } from './components/dialogs/delete-tipo-dialog/delete-tipo-dialog.component';
import { AddPreguntaDialogComponent } from './components/dialogs/add-pregunta-dialog/add-pregunta-dialog.component';
import { UpdatePreguntaDialogComponent } from './components/dialogs/update-pregunta-dialog/update-pregunta-dialog.component';
import { LugaresComponent } from './pages/lugares/lugares.component';
import { PreguntasComponent } from './pages/preguntas/preguntas.component';
import { AddCategoriaDialogComponent } from './components/dialogs/add-categoria-dialog/add-categoria-dialog.component';
import { DelCategoriaDialogComponent } from './components/dialogs/del-categoria-dialog/del-categoria-dialog.component';
import { UpdCategoriaDialogComponent } from './components/dialogs/upd-categoria-dialog/upd-categoria-dialog.component';
import { UpdLugarDialogComponent } from './components/dialogs/upd-lugar-dialog/upd-lugar-dialog.component';
import { AddLugarDialogComponent } from './components/dialogs/add-lugar-dialog/add-lugar-dialog.component';
import { DelLugarDialogComponent } from './components/dialogs/del-lugar-dialog/del-lugar-dialog.component';
import { DelEstudioDialogComponent } from './components/dialogs/del-estudio-dialog/del-estudio-dialog.component';
import { UpdEstudioDialogComponent } from './components/dialogs/upd-estudio-dialog/upd-estudio-dialog.component';
import { AddSolicitudDialogComponent } from './components/dialogs/add-solicitud-dialog/add-solicitud-dialog.component';
import { DelPreguntaDialogComponent } from './components/dialogs/del-pregunta-dialog/del-pregunta-dialog.component';
import { UpdPresentacionDialogComponent } from './components/dialogs/upd-presentacion-dialog/upd-presentacion-dialog.component';
import { DelPresentacionDialogComponent } from './components/dialogs/del-presentacion-dialog/del-presentacion-dialog.component';
import { SubcategoriaComponent } from './pages/subcategoria/subcategoria.component';
import { MarcaComponent } from './pages/marca/marca.component';
import { TiposComponent } from './pages/tipos/tipos.component';
import { UpdSubCategoriaDialogComponent } from './components/dialogs/upd-sub-catategoria-dialog/upd-sub-catategoria-dialog.component';
import { DelSubCategoriaDialogComponent } from './components/dialogs/del-sub-catategoria-dialog/del-sub-catategoria-dialog.component';
import { DelMarcaDialogComponent } from './components/dialogs/del-marca-dialog/del-marca-dialog.component';
import { UpdMarcaDialogComponent } from './components/dialogs/upd-marca-dialog/upd-marca-dialog.component';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BasicInfoDialogComponent } from './components/dialogs/basic-info-dialog/basic-info-dialog.component';
import { AdminHomeComponent } from './pages/AdminHome/AdminHome.component';
@NgModule({
  declarations: [
    EstudiosComponent,
    CategoriasComponent,
    UsuariosComponent,
    PresentacionComponent,
    DashboardComponent,
    DataTableComponent,
    BaseDialogComponent,
    AdminHomeComponent,
    UserInfoDialogComponent,
    AddUserDialogComponent,
    UpdateUserDialogComponent,
    DeleteUserDialogComponent,
    AddTipoDialogComponent,
    UpdateTipoDialogComponent,
    DeleteTipoDialogComponent,
    AddPreguntaDialogComponent,
    UpdatePreguntaDialogComponent,
    LugaresComponent,
    PreguntasComponent,
    AddCategoriaDialogComponent,
    DelCategoriaDialogComponent,
    UpdCategoriaDialogComponent,
    UpdLugarDialogComponent,
    AddLugarDialogComponent,
    DelLugarDialogComponent,
    DelEstudioDialogComponent,
    UpdEstudioDialogComponent,
    AddSolicitudDialogComponent,
    DelPreguntaDialogComponent,
    UpdPresentacionDialogComponent,
    DelPresentacionDialogComponent,
    SubcategoriaComponent,
    MarcaComponent,
    TiposComponent,
    UpdSubCategoriaDialogComponent,
    DelSubCategoriaDialogComponent,
    DelMarcaDialogComponent,
    UpdMarcaDialogComponent,
    BasicInfoDialogComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MaterialModule,
    LocalNgbModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class AdminModule {}
