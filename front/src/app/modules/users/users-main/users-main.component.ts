import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserFormComponent } from '../components/user-form/user-form.component';
import { UserModuleCommunicationService } from '../services/user-module-communication.service';
import { MultiSelectSearchComponent } from '../../shared/components/multi-select-search/multi-select-search.component';
import { MultiSelectModel } from '../../shared/model/multi-select-model';
import { UsersApiService } from '../services/users-api.service';
import { SharedApiService } from '../../shared/services/shared-api.service';
import { ServiceModel } from '../../shared/model/service-model';

@Component({
  selector: 'app-users-main',
  templateUrl: './users-main.component.html',
  styleUrls: ['./users-main.component.css'] 
})
export class UsersMainComponent {
  constructor(
    private dialog: MatDialog,
    private communicationService: UserModuleCommunicationService,
    private usersApiService: UsersApiService,
    private sharedApiService: SharedApiService
  ) { } 
  userId = -1;
  services: ServiceModel[] = [];
  selectedServices: ServiceModel[] = [];
  selectedServiceIds: number[] = [];

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
          this.retrieveServiceData();
        }
      }
    )
  }

  addServiceAccess(){
    if (this.userId === -1){
      console.log('No user selected');
      return;
    }

    const dialogRef = this.dialog.open(MultiSelectSearchComponent, {
      width: '400px',
      data: new MultiSelectModel(this.services, this.selectedServices)
    });
    dialogRef.afterClosed().subscribe((result: ServiceModel[]) => {
      if (result && result.length > 0) {
        // Assuming that result is an array of ServiceModel instances
        this.selectedServiceIds = result.map(service => service.id);
        this.usersApiService.setUserServiceAccess(this.userId, this.selectedServiceIds).subscribe({
          next: (response) => {
            console.log('Service access updated successfully:', response);
          },
          error: (error) => {
            console.error('Failed to update service access:', error);
          }
        });
      }
    });
  }

  addClientAccess(){
    const dialogRef = this.dialog.open(MultiSelectSearchComponent, {
      width: '250px',
      data: {}
    });
  }

  retrieveServiceData(){
    this.usersApiService.getUserServicesList(this.userId).subscribe(
      res => {
        this.selectedServices = res;
      }
    );

    this.sharedApiService.getServicesList().subscribe(
      res => {
        this.services = res;
      }
    );
  }
}
