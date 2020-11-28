import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EncuestadoRoutingModule } from './encuestado-routing.module';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { HomeComponent } from './pages/Home/Home.component';
import { MaterialModule } from '../material.module';


@NgModule({
  declarations: [
    ToolbarComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    EncuestadoRoutingModule,
    MaterialModule
  ]
})
export class EncuestadoModule { }
