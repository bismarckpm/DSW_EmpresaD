import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AnalistaModule } from './analista/analista.module';
import { AdminModule } from './admin/admin.module';
import { ClienteModule } from './cliente/cliente.module';
import { EncuestadoModule } from './encuestado/encuestado.module';
import { SharedModule } from './shared/shared.module';
import { MaterialModule } from './material.module';
import { LocalNgbModule } from './ngbootstrap.module';
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AnalistaModule,
    HttpClientModule,
    AppRoutingModule,
    SharedModule,
    EncuestadoModule,
    ClienteModule,
    AnalistaModule,
    AdminModule,
    MaterialModule,
    AppRoutingModule,
    LocalNgbModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
