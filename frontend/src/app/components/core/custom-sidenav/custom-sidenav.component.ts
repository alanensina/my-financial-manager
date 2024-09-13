import { Component, computed, Input, signal, OnInit } from '@angular/core';
import { MenuItem } from '../../../models/menu-type';
import { MenuService } from '../../../services/main-menu/menu.service';

@Component({
  selector: 'app-custom-sidenav',
  templateUrl: './custom-sidenav.component.html',
  styleUrl: './custom-sidenav.component.css'
})
export class CustomSidenavComponent implements OnInit {

  user: any;

  sideNavCollapsed = signal(false);
  @Input() set collapsed(val: boolean) {
    this.sideNavCollapsed.set(val)
  }

  constructor(
    private menuService: MenuService
  ) { }


  ngOnInit(): void {
    this.user = this.menuService.getUser();
  }

  menuItems = signal<MenuItem[]>([
    {
      icon: 'dashboard',
      label: 'Dashboard',
      route: 'dashboard',
    },
    {
      icon: 'account_balance_wallet',
      label: 'Cash control',
      route: 'cash-control',
    },
    {
      icon: 'trending_up',
      label: 'Investments',
      route: 'investments',
    }

  ]);

  profilePicSize = computed(() => this.sideNavCollapsed() ? '32' : '100');


}
