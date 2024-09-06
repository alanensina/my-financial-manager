import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PopupComponent } from '../../components/utils/popup/popup.component';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor(private dialog: MatDialog ) { }

  displayMessage(message :any){
    this.dialog.open(PopupComponent,{
      width:'200px',
      data: message
    })
  }
}
