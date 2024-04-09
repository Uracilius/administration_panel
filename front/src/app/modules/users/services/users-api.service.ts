import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class UsersApiService {
  path = environment.USERS_API_URL;
  
  constructor(private http: HttpClient) { }

  getUsersList(page:number, pageSize: number): Observable<any> {
    return this.http.post<any>(`${this.path}/getUsers`, {page, pageSize});
  }

  getUserServicesList(userId: number): Observable<any> {
    return this.http.post<any>(`${this.path}/getUserServices?userId=${userId}`, {});
  }
}
 