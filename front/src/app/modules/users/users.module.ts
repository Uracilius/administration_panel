import { NgModule } from '@angular/core';
import { UsersMainComponent } from './users-main/users-main.component';
import { UsersRoutingModule } from './users-routing.module';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../../assets/material/material.module';
import { SharedModule } from '../shared/shared.module';
import { UserServicesTableComponent } from './components/user-services-table/user-services-table.component';
import { UserModuleCommunicationService } from './services/user-module-communication.service';
import { UserClientsTableComponent } from './components/user-clients-table/user-clients-table.component';
import { UsersTableComponent } from './components/users-table/users-table.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    UsersMainComponent,
    UsersTableComponent,
    UserServicesTableComponent,
    UserClientsTableComponent,
    UserFormComponent,
  ],
  imports: [
    UsersRoutingModule,
    MaterialModule,
    CommonModule,
    SharedModule,
    ReactiveFormsModule
  ],
  providers: [
    UserModuleCommunicationService
  ]
})
export class UsersModule { }
