import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminHomeComponent } from './pages/AdminHome/AdminHome.component';
import { CategoriasComponent } from './pages/categorias/categorias.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { EstudiosComponent } from './pages/estudios/estudios.component';
import { LugaresComponent } from './pages/lugares/lugares.component';
import { PreguntasComponent } from './pages/preguntas/preguntas.component';
import { PresentacionComponent } from './pages/presentacion/presentacion.component';
import { UsuariosComponent } from './pages/usuarios/usuarios.component';
import { MarcaComponent } from './pages/marca/marca.component';
import { SubcategoriaComponent } from './pages/subcategoria/subcategoria.component';
import { TiposComponent } from './pages/tipos/tipos.component';

const routes: Routes = [
  {
    path:'administrador', 
    component:DashboardComponent,
    children:[
      {path:'estudios',component:EstudiosComponent, pathMatch:'prefix'},
      {path:'categorias',component:CategoriasComponent, pathMatch:'prefix'},
      {path:'subcategorias',component:SubcategoriaComponent, pathMatch:'prefix'},
      {path:'marcas',component:MarcaComponent, pathMatch:'prefix'},
      {path:'lugares',component:LugaresComponent, pathMatch:'prefix'},
      {path:'tipos',component:TiposComponent, pathMatch:'prefix'},
      {path:'preguntas',component:PreguntasComponent, pathMatch:'prefix'},
      {path:'presentaciones',component:PresentacionComponent, pathMatch:'prefix'},
      {path:'usuarios',component:UsuariosComponent, pathMatch:'prefix'},
      {path:'home',component:AdminHomeComponent, pathMatch:'prefix'},
      {path:'',redirectTo:'administrador/home',pathMatch:'full'},
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
