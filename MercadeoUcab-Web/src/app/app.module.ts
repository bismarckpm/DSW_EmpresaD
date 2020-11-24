import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AnalistaComponent } from './analista/analista.component';

@NgModule({
  declarations: [
    AppComponent,
    AnalistaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
 // providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
