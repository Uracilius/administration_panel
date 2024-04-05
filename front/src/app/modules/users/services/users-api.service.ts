import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { environment } from '../../../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class UsersApiService {
  path = environment.USERS_API_URL;
  
  constructor(private http: HttpClient) { }

  getUsersTable(page:number, pageSize: number): Observable<any> {
    return this.http.post<any>(`${this.path}/getUsersTable`, {page, pageSize});
  }

}
 