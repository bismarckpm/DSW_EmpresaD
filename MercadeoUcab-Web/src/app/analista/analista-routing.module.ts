import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AnalistaOverviewComponent } from './analista-overview/analista-overview.component';
import { AnalistaTasksComponent } from './analista-tasks/analista-tasks.component';
import { AnalistaComponent } from './analista.component';
import {LandingAnalistaComponent } from './landing-analista/landing-analista.component';

const routes: Routes = [
  {
    path:'analist', 
    component:AnalistaComponent,
    children:[
      //ESTUDIO PAGE
      { path: 'overview', component: AnalistaOverviewComponent, pathMatch:"full"},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AnalistaRoutingModule {
  
}
