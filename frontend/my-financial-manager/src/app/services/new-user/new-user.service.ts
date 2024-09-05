import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../models/user';

@Injectable({
  providedIn: 'root'
})
export class NewUserService {

  private apiUrl: string = environment.apiUrl + '/user';

  constructor(private http: HttpClient) { }

  addUser(user: User): Observable<void> {
    return this.http.post<void>(this.apiUrl, user);
  }
}
