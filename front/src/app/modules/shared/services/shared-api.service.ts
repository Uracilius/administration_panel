import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SharedApiService {
  service_path = environment.SERVICES_API_URL;
  client_path = environment.CLIENTS_API_URL;
  constructor(
    private http: HttpClient
  ) {}

  getServicesList() {
    return this.http.get<any>(`${this.service_path}`);
  }

  getClientsList() {
    return this.http.get<any>(`${this.client_path}`);
  }
}
