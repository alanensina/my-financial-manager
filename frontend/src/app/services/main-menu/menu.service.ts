import { Injectable } from '@angular/core';
import { User } from '../../models/user';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private user: any;

  constructor() { }

  setUser(user: User){
    this.user = user;
  }

  getUser(){
    return this.user;
  }
}
