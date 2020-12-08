import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminHomeComponent } from './pages/AdminHome/AdminHome.component';
import { CategoriasComponent } from './pages/categorias/categorias.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { EstudiosComponent } from './pages/estudios/estudios.component';
import { LugaresComponent } from './pages/lugares/lugares.component';
import { PreguntasComponent } from './pages/preguntas/preguntas.component';
import { UsuariosComponent } from './pages/usuarios/usuarios.component';


const routes: Routes = [
  {
    path:'administrador', 
    component:DashboardComponent,
    children:[
      {path:'estudios',component:EstudiosComponent, pathMatch:'prefix'},
      {path:'categorias',component:CategoriasComponent, pathMatch:'prefix'},
      {path:'lugares',component:LugaresComponent, pathMatch:'prefix'},
      {path:'preguntas',component:PreguntasComponent, pathMatch:'prefix'},
      {path:'usuarios',component:UsuariosComponent, pathMatch:'prefix'},
      {path:'home',component:AdminHomeComponent, pathMatch:'prefix'},
      {path:'',redirectTo:'administrador/home', pathMatch:'prefix'},
    ]
  },
  {path:'',redirectTo:'/administrador', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
