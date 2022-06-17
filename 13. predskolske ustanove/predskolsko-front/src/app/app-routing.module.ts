import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateDeteComponent } from './components/create-dete/create-dete.component';
import { CreateKonkursComponent } from './components/create-konkurs/create-konkurs.component';
import { CreateSluzbenikComponent } from './components/create-sluzbenik/create-sluzbenik.component';
import { CreateVrticComponent } from './components/create-vrtic/create-vrtic.component';
import { KonkursComponent } from './components/konkurs/konkurs.component';
import { SluzbenikComponent } from './components/sluzbenik/sluzbenik.component';
import { UpdateSluzbenikComponent } from './components/update-sluzbenik/update-sluzbenik.component';
import { UpdateVrticComponent } from './components/update-vrtic/update-vrtic.component';
import { VrticComponent } from './components/vrtic/vrtic.component';

const routes: Routes = [
  { path: "", redirectTo: "home", pathMatch: "full" },
  { path: 'konkursi', component: KonkursComponent},
  { path: 'vrtici', component: VrticComponent},
  { path: 'createVrtic', component: CreateVrticComponent},
  { path: 'createKonkurs', component: CreateKonkursComponent},
  { path: 'createDete', component: CreateDeteComponent},
  { path: 'updateVrtic/:id', component: UpdateVrticComponent},
  { path: 'sluzbenik', component: SluzbenikComponent},
  { path: 'createSluzbenik', component: CreateSluzbenikComponent},
  { path: 'updateSluzbenik/:id', component: UpdateSluzbenikComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
