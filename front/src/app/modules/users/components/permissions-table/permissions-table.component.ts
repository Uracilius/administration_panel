import { Component } from '@angular/core';
import { UsersApiService } from '../../services/users-api.service';
import { UserModuleCommunicationService } from '../../services/user-module-communication.service';

@Component({
  selector: 'app-permissions-table',
  templateUrl: './permissions-table.component.html',
  styleUrl: './permissions-table.component.css'
})
export class UsersListComponent {
  displayedColumns= ['id', 'login', 'description', 'status', 'created', ];
  dataSource = [{id:"hello",org_id:"world", services:"hello", username:"world", password:"hello"}]
  constructor(
    private usersApiService: UsersApiService,
    private communicationService: UserModuleCommunicationService
  ) { }
  ngOnInit() {
    this.populateTable();
  }

  populateTable(){
    this.usersApiService.getUsersList(0, 10).subscribe(
      data => {
        if (data){
          this.dataSource = data;
        }
      }
    )
  }

  userClick(row: any){
    this.communicationService.selectedUserId$.next(row.id)
  }
}
