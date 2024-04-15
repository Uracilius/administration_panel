import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserFormComponent } from '../components/user-form/user-form.component';
import { UserModuleCommunicationService } from '../services/user-module-communication.service';

@Component({
  selector: 'app-users-main',
  templateUrl: './users-main.component.html',
  styleUrls: ['./users-main.component.css'] 
})
export class UsersMainComponent {
  constructor(
    private dialog: MatDialog,
    private communicationService: UserModuleCommunicationService
  ) { } 
  userId = -1;

  ngOnInit(){
    this.initSubscribe();
  }

  initializeUserForm(): void {
    const dialogRef = this.dialog.open(UserFormComponent, {
      width: '250px',
    });
  }

  initSubscribe(){
    this.communicationService.selectedUserId$.subscribe(
      userId => {
        if (userId !== -1){
          this.userId=userId;
        }
      }
    )
  }
}
