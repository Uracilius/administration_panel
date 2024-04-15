import { Component, ViewChild } from '@angular/core';
import { UsersApiService } from '../../services/users-api.service';
import { UserModuleCommunicationService } from '../../services/user-module-communication.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-user-clients-table',
  templateUrl: './user-clients-table.component.html',
  styleUrl: './user-clients-table.component.css'
})
export class UserClientsTableComponent {
  displayedColumns= ['code', 'name', 'description', 'actions' ];
  dataSource = new MatTableDataSource<any>(); 
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  userId=-1;
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

    this.communicationService.selectedUserId$.subscribe(
      userId => {
        if (userId !== -1){
          this.userId=userId;
        }
      }
    )
  }

  populateTable(userId: number){
    this.usersApiService.getUserClientsList(userId).subscribe(
      data => {
        if (data){
          this.dataSource.data = data; 
          this.dataSource.paginator = this.paginator;
        }
      }
    )
  }

  deleteItem(element: any){
    this.usersApiService.deleteUserClient(this.userId, element.id).subscribe(
      response => {
        if (response){
          console.log(response)
        }
      }
    )
  }

  userClick(row: any){
    
  }
}
