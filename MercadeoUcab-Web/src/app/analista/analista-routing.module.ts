import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AnalistaOverviewComponent } from './Pages/overview/analista-overview.component';
import { AnalistaTasksComponent } from './Pages//tasks/analista-tasks.component';
import { AnalistaComponent } from './Pages/dashboard/analista.component';


const routes: Routes = [
  {
  path:'analist', component:AnalistaComponent,
  children:[
    {path: 'overview', component: AnalistaOverviewComponent, pathMatch:"full"},
    {path: 'tasks', component: AnalistaTasksComponent, pathMatch:"full"},
    ]
  },
  {path:'analist',redirectTo:'/analist/overview', pathMatch:'prefix',}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AnalistaRoutingModule {
  
}
