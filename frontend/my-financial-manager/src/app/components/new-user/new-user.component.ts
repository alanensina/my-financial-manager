import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NewUserService } from '../../services/new-user/new-user.service';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrl: './new-user.component.css'
})
export class NewUserComponent implements OnInit{

  loginForm: FormGroup = new FormGroup({});

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private activatedRouter: ActivatedRoute,
    private newUserService: NewUserService 
  ){}

  ngOnInit(){
    this.loginForm = this.formBuilder.group({
      name: ['', Validators.required],
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

  cancel(){
    this.router.navigate(['']);
  }

}
