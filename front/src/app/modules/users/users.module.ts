import { NgModule } from '@angular/core';
import { UsersMainComponent } from './users-main/users-main.component';
import { UsersRoutingModule } from './users-routing.module';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../../assets/material/material.module';
import { PermissionsTableComponent } from './components/permissions-table/permissions-table.component';

@NgModule({
  declarations: [
    UsersMainComponent,
    PermissionsTableComponent
  ],
  imports: [
    UsersRoutingModule,
    MaterialModule,
    CommonModule
  ]
})
export class UsersModule { }
