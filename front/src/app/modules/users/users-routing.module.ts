import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersMainComponent } from './users-main/users-main.component';
const routes: Routes = [
  {
    path: '', component: UsersMainComponent,
  },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class UsersRoutingModule { }
