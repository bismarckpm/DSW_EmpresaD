import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EncuestadoRoutingModule } from './encuestado-routing.module';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { HomeComponent } from './pages/Home/Home.component';
import { MaterialModule } from '../material.module';
import { CarrouselComponent } from './components/carrousel/carrousel.component';
import { StudioCardComponent } from './components/studioCard/studioCard.component';
import { ResponderEncuestaComponent } from './pages/responder-encuesta/responder-encuesta.component';
import { EncuestasPendientesComponent } from './pages/encuestas-pendientes/encuestas-pendientes.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LocalNgbModule } from '../ngbootstrap.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    ToolbarComponent,
    StudioCardComponent,
    CarrouselComponent,
    HomeComponent,
    ResponderEncuestaComponent,
    EncuestasPendientesComponent,
    DashboardComponent,
  ],
  imports: [
    CommonModule,
    EncuestadoRoutingModule,
    MaterialModule,
    LocalNgbModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class EncuestadoModule {}
