import { Component , OnInit, Inject} from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog'

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrl: './popup.component.css'
})
export class PopupComponent implements OnInit{

  message :string = '';

  constructor(
    private dialogRef: MatDialogRef<PopupComponent>,
    @Inject(MAT_DIALOG_DATA) private data:any){}
  
  ngOnInit(): void {
    this.message = this.data;
  }

  onCloseClick(){
    this.dialogRef.close();
  }
}
