import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CollectJwtComponent } from './collect-jwt/collect-jwt.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [

  {
    path: "login", component: LoginComponent,
  },
  {
    path: "collect_jwt", component: CollectJwtComponent,
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
