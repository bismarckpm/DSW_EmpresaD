import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AnalistaComponent } from './analista/analista.component';
import { AnalistaModule } from './analista/analista.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatGridListModule } from '@angular/material/grid-list';
import { AdminModule } from './admin/admin.module';
import { ClienteModule } from './cliente/cliente.module';
import { EncuestadoModule } from './encuestado/encuestado.module';
import { SharedModule } from './shared/shared.module';

@NgModule({
  declarations: [
    AppComponent,
    AnalistaComponent
  ],
  imports: [
    BrowserModule,
    AnalistaModule,
    AppRoutingModule,
    MatGridListModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    SharedModule,
    EncuestadoModule,
    ClienteModule,
    AnalistaModule,
    AdminModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
