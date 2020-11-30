import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnalistaRoutingModule } from './analista-routing.module';
import { AnalistaTasksComponent } from './Pages/tasks/analista-tasks.component';
import { AnalistaOverviewComponent } from './Pages/overview/analista-overview.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { EstudioRealizarComponent } from './Pages/estudio-realizar/estudio-realizar.component';
import {MatTableModule} from '@angular/material/table';
import {MatRippleModule} from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { EstudioService } from './../shared/Services/estudio/estudio.service';


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
    MatTableModule,
    MatRippleModule,
    MatIconModule,
    BrowserAnimationsModule
  ],
  providers:[
    EstudioService
  ]
})
export class AnalistaModule { }
