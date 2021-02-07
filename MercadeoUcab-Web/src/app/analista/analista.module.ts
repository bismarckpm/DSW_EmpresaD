import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnalistaRoutingModule } from './analista-routing.module';
import { AnalistaTasksComponent } from './Pages/tasks/analista-tasks.component';
import { AnalistaOverviewComponent } from './Pages/overview/analista-overview.component';
import { EstudioRealizarComponent } from './Pages/estudio-realizar/estudio-realizar.component';
import { EstudioService } from './../shared/Services/estudio/estudio.service';

import { MaterialModule } from '../material.module';
import { LocalNgbModule } from '../ngbootstrap.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AnalistaComponent } from './Pages/dashboard/analista.component';
import { BasicInfoDialogComponent } from './components/dialogs/basic-info-dialog/basic-info-dialog.component';
import { EncuestaDialogComponent } from './components/encuesta-dialog/encuesta-dialog.component';

@NgModule({
  declarations: [
    AnalistaTasksComponent,
    AnalistaOverviewComponent,
    EstudioRealizarComponent,
    AnalistaComponent,
    BasicInfoDialogComponent,
    EncuestaDialogComponent,
  ],
  imports: [
    CommonModule,
    AnalistaRoutingModule,
    MaterialModule,
    LocalNgbModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [EstudioService],
})
export class AnalistaModule {}
