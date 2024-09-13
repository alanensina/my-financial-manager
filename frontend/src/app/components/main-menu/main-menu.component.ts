import { Component, computed, OnInit, signal } from '@angular/core';
import { MenuService } from '../../services/main-menu/menu.service';

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrl: './main-menu.component.css'
})
export class MainMenuComponent implements OnInit{

  user: any;
  currentDate: Date = new Date();
  collapsed = signal(false);
  sideNavWidth = computed(() => this.collapsed() ? '65px' : '250px');

  constructor(
    private menuService: MenuService
  ){}


  ngOnInit(): void {
    this.user = this.menuService.getUser();
    setInterval(() => {
      this.currentDate = new Date();
    }, 1000);
  }
}
