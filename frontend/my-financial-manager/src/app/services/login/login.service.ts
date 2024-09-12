import { HttpClient, HttpErrorResponse, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../../models/user';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl: string = environment.apiUrl + '/login';

  constructor(private http: HttpClient) { }

  login(user: User): Observable<HttpResponse<any>> {

    let params = new HttpParams()
    .set('email',  user.email)
    .set('password',  user.password);

    return this.http.get<any>(this.apiUrl, {
      params: params,
      observe: 'response'  
    }).pipe(catchError(this.handleLoginError));
  }

  private handleLoginError(error: HttpErrorResponse){
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
