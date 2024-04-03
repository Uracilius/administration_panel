import { NgModule } from '@angular/core';
import { UsersMainComponent } from './users-main/users-main.component';
import { UsersRoutingModule } from './users-routing.module';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../../assets/material/material.module';



@NgModule({
  declarations: [
    UsersMainComponent
  ],
  imports: [
    UsersRoutingModule,
    MaterialModule,
    CommonModule
  ]
})
export class UsersModule { }
