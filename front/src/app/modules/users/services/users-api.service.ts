import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { FormGroup } from '@angular/forms';


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

  getUserClientsList(userId: number): Observable<any> {
    return this.http.post<any>(`${this.path}/getUserClients?userId=${userId}`, {});
  }

  addUser(formData: FormGroup): Observable<any> {
    return this.http.post<any>(`${this.path}/addUser`, formData.value);
  }

  editUser(formData: FormGroup): Observable<any> {
    return this.http.patch<any>(`${this.path}/editUser`, formData.value);
  }

  deleteUser(userId: number): Observable<any> {
    return this.http.delete<any>(`${this.path}/deleteUser?userId=${userId}`, {});
  }

  deleteUserClient(userId: number, clientId: number): Observable<any> {
    return this.http.delete<any>(`${this.path}/deleteUserClientAccess?userId=${userId}&clientId=${clientId}`, {});
  }
}
 