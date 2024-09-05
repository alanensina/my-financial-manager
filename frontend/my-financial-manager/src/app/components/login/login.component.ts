import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../../services/login/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  
  loginForm: FormGroup = new FormGroup({});

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private activatedRouter: ActivatedRoute,
    private loginService: LoginService
  ){}

  ngOnInit(){
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    })
  }

  onSubmit(){
    if(this.loginForm.valid){
      
    }     
  }

  newUser(){
    this.router.navigate(['/new-user']);
  }
}
