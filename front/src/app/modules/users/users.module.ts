import { NgModule } from '@angular/core';
import { UsersMainComponent } from './users-main/users-main.component';
import { UsersRoutingModule } from './users-routing.module';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../../assets/material/material.module';
import { UsersListComponent } from './components/permissions-table/permissions-table.component';
import { SharedModule } from '../shared/shared.module';
import { UserServicesTableComponent } from './components/user-services-table/user-services-table.component';
import { UserModuleCommunicationService } from './services/user-module-communication.service';

@NgModule({
  declarations: [
    UsersMainComponent,
    UsersListComponent,
    UserServicesTableComponent
  ],
  imports: [
    UsersRoutingModule,
    MaterialModule,
    CommonModule,
    SharedModule
  ],
  providers: [
    UserModuleCommunicationService
  ]
})
export class UsersModule { }
