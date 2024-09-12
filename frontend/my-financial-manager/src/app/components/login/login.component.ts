import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login/login.service';
import { User } from '../../models/user';
import { UtilsService } from '../../services/utils/utils.service';
import { MenuService } from '../../services/main-menu/menu.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup = new FormGroup({});
  

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private loginService: LoginService,
    private utilsService: UtilsService,
    private menuService: MenuService
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    })
  }

  onSubmit() {
    if (this.loginForm.valid) {

      let form: User = this.loginForm.value;      

      this.loginService.login(form).subscribe({
        next: data => {
          this.menuService.setUser(data.body);
          this.router.navigate(['/home']);
        },

        error: err => {
          if (err.message === '400') {
            this.utilsService.openSnackBar('Invalid credentials.');
          }
          if (err.message === '404') {
            this.utilsService.openSnackBar('User not found.');
          }
        }
      });
    }
  }

  newUser(){
    this.router.navigate(['/new-user']);
  }
}
