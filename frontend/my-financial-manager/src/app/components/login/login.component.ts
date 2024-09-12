import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../../services/login/login.service';
import { User } from '../../models/user';
import { UtilsService } from '../../services/utils/utils.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup = new FormGroup({});
  //private _snackBar = inject(MatSnackBar);

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private activatedRouter: ActivatedRoute,
    private loginService: LoginService,
    private utilsService: UtilsService
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    })
  }

  onSubmit() {
    if (this.loginForm.valid) {

      let user: User = this.loginForm.value;
      

      this.loginService.login(user).subscribe({
        next: data => {
          console.log(data)





          this.router.navigate(['/home']);
        },

        error: err => {
          if (err.message === '404') {
            
          }
        }
      });
    }
  }

  newUser(){
    this.router.navigate(['/new-user']);
  }
}
