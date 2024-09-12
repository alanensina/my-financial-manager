import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { NewUserComponent } from './components/new-user/new-user.component';
import { MainMenuComponent } from './components/main-menu/main-menu.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'new-user', component: NewUserComponent },
  { path: 'home', component: MainMenuComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
