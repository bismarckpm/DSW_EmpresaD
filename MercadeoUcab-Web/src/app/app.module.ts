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
import { CoreModule } from './core/core.module';
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    SharedModule,
    EncuestadoModule,
    ClienteModule,
    AnalistaModule,
    AdminModule,
    AppRoutingModule,
    MaterialModule,
    LocalNgbModule,
    CoreModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
