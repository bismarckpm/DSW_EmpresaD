import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';

import { AnalistaRoutingModule } from './analista-routing.module';
import { LandingAnalistaComponent } from './landing-analista/landing-analista.component';
import { AnalistaTasksComponent } from './analista-tasks/analista-tasks.component';
import { AnalistaOverviewComponent } from './analista-overview/analista-overview.component';
import { AnalistaComponent } from './analista.component';
import { MaterialModule } from '../material.module';

@NgModule({
  declarations: [
    LandingAnalistaComponent,
    AnalistaTasksComponent,
    AnalistaOverviewComponent,
    AnalistaComponent
  ],
  imports: [
    CommonModule,
    AnalistaRoutingModule,
    MaterialModule
  ],
})
export class AnalistaModule { }
