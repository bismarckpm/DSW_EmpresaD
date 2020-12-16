import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminModule } from './admin/admin.module';
import { AnalistaModule } from './analista/analista.module';
import { ClienteModule } from './cliente/cliente.module';
import { EncuestadoModule } from './encuestado/encuestado.module';
import { SharedModule } from './shared/shared.module';
import { MaterialModule } from './material.module';
import { LocalNgbModule } from './ngbootstrap.module';
import { CoreModule } from './core/core.module';
import { AuthModule } from './auth/auth.module';
@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserAnimationsModule,
    //BrowserModule,
    HttpClientModule,
    //SharedModule,
    EncuestadoModule,
    ClienteModule,
    AnalistaModule,
    AdminModule,
    AuthModule,
    //MaterialModule,
    //LocalNgbModule,
    CoreModule,
    AppRoutingModule,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
