import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateKonkursComponent } from './components/create-konkurs/create-konkurs.component';
import { CreateVrticComponent } from './components/create-vrtic/create-vrtic.component';
import { KonkursComponent } from './components/konkurs/konkurs.component';
import { VrticComponent } from './components/vrtic/vrtic.component';

const routes: Routes = [
  { path: "", redirectTo: "home", pathMatch: "full" },
  { path: 'konkursi', component: KonkursComponent},
  { path: 'vrtici', component: VrticComponent},
  { path: 'createVrtici', component: CreateVrticComponent},
  { path: 'createKonkurs', component: CreateKonkursComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
