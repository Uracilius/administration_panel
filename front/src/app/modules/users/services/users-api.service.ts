import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { FormGroup } from '@angular/forms';


@Injectable({
  providedIn: 'root'
})
export class UsersApiService {
  path = environment.USERS_API_URL;
  
  constructor(private http: HttpClient) { }

  getUsersList(page: number, pageSize: number): Observable<any> {
    let params = new HttpParams().set('page', page).set('pageSize', pageSize);
    return this.http.get<any>(`${this.path}`, { params });
  }

  getUserServicesList(userId: number): Observable<any> {
    return this.http.get<any>(`${this.path}/${userId}/services`);
  }

  getUserClientsList(userId: number): Observable<any> {
    return this.http.get<any>(`${this.path}/${userId}/clients`);
  }

  addUser(formData: FormGroup): Observable<any> {
    return this.http.post<any>(`${this.path}/user`, formData.value);
  }

  setUserServiceAccess(userId: number, serviceIds: number[]): Observable<any[]> {
    return this.http.post<any[]>(`${this.path}/service-access/${userId}`, serviceIds);
  }

  editUser(formData: FormGroup): Observable<any> {
    return this.http.patch<any>(`${this.path}/edit`, formData.value);
  }

  deleteUser(userId: number): Observable<any> {
    return this.http.delete<any>(`${this.path}/${userId}`);
  }

  deleteUserClient(userId: number, clientId: number): Observable<any> {
    return this.http.delete<any>(`${this.path}/client-access/${userId}/${clientId}`);
  }

  deleteUserServiceAccess(userId: number, serviceId: number): Observable<any> {
    return this.http.delete<any>(`${this.path}/service-access/${userId}/${serviceId}`);
  }
}
 