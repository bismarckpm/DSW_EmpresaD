import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './pages/Home/Home.component';
import {EncuestasPendientesComponent} from './pages/encuestas-pendientes/encuestas-pendientes.component';
import {ResponderEncuestaComponent} from './pages/responder-encuesta/responder-encuesta.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';

const routes: Routes = [
  {
    path:'encuestado', 
    component:DashboardComponent,
    children:[
    {path:'home',component:HomeComponent, pathMatch:'prefix'},
    {path:'pending',component:EncuestasPendientesComponent, pathMatch:'prefix'},
    {path:'survey/:id',component:ResponderEncuestaComponent, pathMatch:'prefix'},
    {path:'',redirectTo:'encuestado/home',pathMatch:'full'},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EncuestadoRoutingModule { }
