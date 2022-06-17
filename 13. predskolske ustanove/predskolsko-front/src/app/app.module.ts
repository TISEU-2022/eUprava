import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { KonkursComponent } from './components/konkurs/konkurs.component';
import { DeteComponent } from './components/dete/dete.component';
import { VrticComponent } from './components/vrtic/vrtic.component';
import { SluzbenikComponent } from './components/sluzbenik/sluzbenik.component';
import { CreateVrticComponent } from './components/create-vrtic/create-vrtic.component';
import { FormsModule } from '@angular/forms';
import { CreateKonkursComponent } from './components/create-konkurs/create-konkurs.component';
import { CreateDeteComponent } from './components/create-dete/create-dete.component';
import { UpdateVrticComponent } from './components/update-vrtic/update-vrtic.component';
import { CreateSluzbenikComponent } from './components/create-sluzbenik/create-sluzbenik.component';

@NgModule({
  declarations: [
    KonkursComponent,
    AppComponent,
    DeteComponent,
    VrticComponent,
    SluzbenikComponent,
    CreateVrticComponent,
    CreateKonkursComponent,
    CreateDeteComponent,
    UpdateVrticComponent,
    CreateSluzbenikComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
