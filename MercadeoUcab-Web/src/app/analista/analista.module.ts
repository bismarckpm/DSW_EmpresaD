import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnalistaRoutingModule } from './analista-routing.module';
import { AnalistaTasksComponent } from './Pages/tasks/analista-tasks.component';
import { AnalistaOverviewComponent } from './Pages/overview/analista-overview.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';

@NgModule({
  declarations: [
    AnalistaTasksComponent,
    AnalistaOverviewComponent,
  ],
  imports: [
    CommonModule,
    AnalistaRoutingModule,
    MatGridListModule,
    MatCardModule,
  ],
})
export class AnalistaModule { }
