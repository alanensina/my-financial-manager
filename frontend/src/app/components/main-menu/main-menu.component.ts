import { Component, OnInit } from '@angular/core';
import { MenuService } from '../../services/main-menu/menu.service';

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrl: './main-menu.component.css'
})
export class MainMenuComponent implements OnInit{

  user: any;

  constructor(
    private menuService: MenuService
  ){}


  ngOnInit(): void {
    this.user = this.menuService.getUser();
  }
}
