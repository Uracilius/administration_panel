import { Component } from '@angular/core';
import { UsersApiService } from '../../services/users-api.service';
import { UserModuleCommunicationService } from '../../services/user-module-communication.service';

@Component({
  selector: 'app-user-clients-table',
  templateUrl: './user-clients-table.component.html',
  styleUrl: './user-clients-table.component.css'
})
export class UserClientsTableComponent {
  displayedColumns= ['code', 'name', 'description', 'shep_code' ];
  dataSource = [{id:"hello",org_id:"world", services:"hello", username:"world", password:"hello"}]
  constructor(
    private usersApiService: UsersApiService,
    private communicationService: UserModuleCommunicationService
  ) { }
  ngOnInit() {
    this.initSubscribe();
  }

  initSubscribe(){
    this.communicationService.selectedUserId$.subscribe(
      userId => {
        if (userId !== -1){
          this.populateTable(userId);
        }
      }
    )

  }
  populateTable(userId: number){
    this.usersApiService.getUserClientsList(userId).subscribe(
      data => {
        if (data){
          this.dataSource = data;
        }
      }
    )
  }
  userClick(row: any){
    console.log('user clicked', row)
  }
}
