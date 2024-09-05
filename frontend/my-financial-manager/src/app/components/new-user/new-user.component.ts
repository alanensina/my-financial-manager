import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NewUserService } from '../../services/new-user/new-user.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrl: './new-user.component.css'
})
export class NewUserComponent implements OnInit{

  newUserForm: FormGroup = new FormGroup({});

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private newUserService: NewUserService 
  ){}

  ngOnInit(){
    this.newUserForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    })
  }

  onSubmit(){
    if(this.newUserForm.valid){
      let user: User = this.newUserForm.value;

      this.newUserService.addUser(user).subscribe(() => {
        console.log("User added: " + user);
        this.cancel();
      }); 
    }     
  }

  newUser(){
    this.router.navigate(['/new-user']);
  }

  cancel(){
    this.router.navigate(['']);
  }

}
