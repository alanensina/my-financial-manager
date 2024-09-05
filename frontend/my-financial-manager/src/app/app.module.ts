import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';


import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { NewUserComponent } from './components/new-user/new-user.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NewUserComponent    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatDividerModule,
    MatButtonModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
