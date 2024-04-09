import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserModuleCommunicationService {

  constructor() { }
  public selectedUserId$ = new BehaviorSubject<number>(-1);

}
