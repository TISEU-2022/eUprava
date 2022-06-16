import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { KonkursComponent } from './components/konkurs/konkurs.component';
import { DeteComponent } from './components/dete/dete.component';

@NgModule({
  declarations: [
    KonkursComponent,
    AppComponent,
    DeteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
