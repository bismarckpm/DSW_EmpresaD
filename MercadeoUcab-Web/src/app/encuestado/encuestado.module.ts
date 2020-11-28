import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EncuestadoRoutingModule } from './encuestado-routing.module';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { HomeComponent } from './pages/Home/Home.component';
import { MaterialModule } from '../material.module';
import { CarrouselComponent } from './components/carrousel/carrousel.component';


@NgModule({
  declarations: [
    ToolbarComponent,
    CarrouselComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    EncuestadoRoutingModule,
    MaterialModule
  ]
})
export class EncuestadoModule { }
