import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { KonkursComponent } from './components/konkurs/konkurs.component';

const routes: Routes = [
  { path: "", redirectTo: "home", pathMatch: "full" },
  { path: 'konkursi', component: KonkursComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
