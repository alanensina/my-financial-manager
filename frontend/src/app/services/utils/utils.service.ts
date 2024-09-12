import { inject, Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  private _snackBar = inject(MatSnackBar);

  constructor(private dialog: MatDialog ) { }

  openSnackBar(message: string) {
    this._snackBar.open(message, 'Close');
  }
}
