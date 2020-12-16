import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClienteRoutingModule } from './cliente-routing.module';
import { dashboardClienteComponent } from './Pages/dashboardCliente/dashboardCliente.component';
import { SolicitudComponent } from './Pages/Solicitud/Solicitud.component';
import { UpdateSolicitudDialogComponent } from './components/dialogs/upd-solicitud-dialog/update-solicitud-dialog.component';
import { DataTableClienteComponent } from './components/data-table-cliente/data-table-cliente.component';
import { EstudioComponent } from './Pages/estudio/estudio.component';
import { MaterialModule } from '../material.module';
import { LocalNgbModule } from '../ngbootstrap.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    dashboardClienteComponent,
    SolicitudComponent,
    UpdateSolicitudDialogComponent,
    DataTableClienteComponent,
    EstudioComponent,
  ],
  imports: [
    CommonModule,
    ClienteRoutingModule,
    MaterialModule,
    LocalNgbModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class ClienteModule {}
