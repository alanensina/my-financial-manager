import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { NewUserComponent } from './components/new-user/new-user.component';
import { MainMenuComponent } from './components/main-menu/main-menu.component';
import { InvestmentCreateComponent } from './components/investments/investment-create/investment-create.component';
import { InvestmentListComponent } from './components/investments/investment-list/investment-list.component';
import { CashControlCreateComponent } from './components/cash-control/cash-control-create/cash-control-create.component';
import { CashControlListComponent } from './components/cash-control/cash-control-list/cash-control-list.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', component: LoginComponent },
  { path: 'new-user', component: NewUserComponent },
  { path: 'home', component: MainMenuComponent },
  { path: 'investments/new', component: InvestmentCreateComponent },
  { path: 'home/investments', component: InvestmentListComponent },
  { path: 'cash-control/new', component: CashControlCreateComponent },
  { path: 'home/cash-control', component: CashControlListComponent },
  { path: 'home/dashboard', component: DashboardComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
