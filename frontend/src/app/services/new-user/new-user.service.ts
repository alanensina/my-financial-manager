import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { User } from '../../models/user';

@Injectable({
  providedIn: 'root'
})
export class NewUserService {

  private apiUrl: string = environment.apiUrl + '/user';

  constructor(private http: HttpClient) { }

  addUser(user: User): Observable<HttpResponse<any>> {
    return this.http.post<void>(this.apiUrl, user, {observe: 'response'}).pipe(catchError(this.handleErrorAddUser));
  }

  private handleErrorAddUser(error: HttpErrorResponse){
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      errorMessage = `${error.status}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }
}
