import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AnalistaRoutingModule } from './analista/analista-routing.module';
import { AppComponent } from './app.component';

const routes: Routes = [
  {path:'analist', loadChildren:() => import('./analista/analista.module').then(m => m.AnalistaModule)},
  {path:'', redirectTo:'/analist', pathMatch:'full' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    AnalistaRoutingModule,
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
