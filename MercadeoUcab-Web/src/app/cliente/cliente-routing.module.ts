import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {dashboardClienteComponent} from "./Pages/dashboardCliente/dashboardCliente.component";
import {SolicitudComponent} from "./Pages/Solicitud/Solicitud.component";
import {EstudioComponent} from "./Pages/estudio/estudio.component";
import {ClienteHomeComponent} from "./Pages/ClienteHome/ClienteHome.component";


const routes: Routes = [
  {
    path:'cliente',
    component:dashboardClienteComponent,
    children:[
      {path:'solicitudes',component:SolicitudComponent, pathMatch:'prefix'},
      {path:'estudios',component:EstudioComponent, pathMatch:'prefix'},
      {path:'home',component:ClienteHomeComponent, pathMatch:'prefix'},
      {path:'',redirectTo:'cliente/home',pathMatch:'full'},
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClienteRoutingModule { }
