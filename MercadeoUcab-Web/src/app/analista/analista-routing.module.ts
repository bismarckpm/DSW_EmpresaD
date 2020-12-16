import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AnalistaOverviewComponent } from './Pages/overview/analista-overview.component';
import { AnalistaTasksComponent } from './Pages//tasks/analista-tasks.component';
import { AnalistaComponent } from './Pages/dashboard/analista.component';
import { EstudioRealizarComponent } from './Pages/estudio-realizar/estudio-realizar.component';

const routes: Routes = [
  {
    path: '',
    component: AnalistaComponent,
    children: [
      {
        path: 'overview',
        component: AnalistaOverviewComponent,
        pathMatch: 'prefix',
      },
      { path: 'tasks', component: AnalistaTasksComponent, pathMatch: 'prefix' },
      {
        path: 'estudio/:id',
        component: EstudioRealizarComponent,
        pathMatch: 'prefix',
      },
      { path: '', redirectTo: 'analist/overview', pathMatch: 'prefix' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AnalistaRoutingModule {}
