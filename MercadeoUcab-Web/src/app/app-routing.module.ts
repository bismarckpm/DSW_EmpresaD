import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AnalistaRoutingModule } from './analista/analista-routing.module';
import { AnalistaComponent } from './analista/analista.component';
import {AnalistaOverviewComponent} from './analista/analista-overview/analista-overview.component';
import {AnalistaTasksComponent} from './analista/analista-tasks/analista-tasks.component';
import { AppComponent } from './app.component';

const routes: Routes = [
  //{path:'home', component:AppComponent},
  {path:'analist', 
  //component:AnalistaComponent,
  loadChildren: () => import(`./analista/analista.module`).then(m => m.AnalistaModule)},
  { path: '', pathMatch: 'full', component:AppComponent },
  //{path:'', component:AppComponent},

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    AnalistaRoutingModule,
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
