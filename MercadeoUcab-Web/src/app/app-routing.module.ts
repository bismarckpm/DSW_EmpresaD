import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CanPassGuard } from '@core/guards/can-pass.guard';

const routes: Routes = [
  {
    path: '',
    loadChildren: './auth/auth.module#AuthModule',
  },
  {
    path: 'encuestado',
    //canLoad: [CanPassGuard],
    loadChildren: './encuestado/encuestado.module#EncuestadoModule',
  },
  {
    path: 'administrador',
    //canLoad: [CanPassGuard],
    loadChildren: './admin/admin.module#AdminModule',
  },
  {
    path: 'cliente',
    //canLoad: [CanPassGuard],
    loadChildren: './cliente/cliente.module#ClienteModule',
  },
  {
    path: 'analista',
    //canLoad: [CanPassGuard],
    loadChildren: './analista/analista.module#AnalistaModule',
  },
  {
    path: '**',
    redirectTo: '',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
