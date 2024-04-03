import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  path = environment.LOGIN_API_URL;
  
  constructor(private http: HttpClient) { }

  login(formData: FormGroup): Observable<any> {
    return this.http.post<any>(`${this.path}/login`, formData.value);
  }

}
 