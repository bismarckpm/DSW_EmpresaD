import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnalistaRoutingModule } from './analista-routing.module';
import { AnalistaTasksComponent } from './Pages/tasks/analista-tasks.component';
import { AnalistaOverviewComponent } from './Pages/overview/analista-overview.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { EstudioRealizarComponent } from './Pages/estudio-realizar/estudio-realizar.component';
import {MatTableModule} from '@angular/material/table';


@NgModule({
  declarations: [
    AnalistaTasksComponent,
    AnalistaOverviewComponent,
    EstudioRealizarComponent,
  ],
  imports: [
    CommonModule,
    AnalistaRoutingModule,
    MatGridListModule,
    MatCardModule,
    MatTableModule
  ],
})
export class AnalistaModule { }
