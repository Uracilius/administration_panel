import { Component, ViewChild } from '@angular/core';
import { UsersApiService } from '../../services/users-api.service';
import { UserModuleCommunicationService } from '../../services/user-module-communication.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-users-table',
  templateUrl: './users-table.component.html',
  styleUrl: './users-table.component.css'
})
export class UsersTableComponent {
  displayedColumns= ['login', 'description', 'status', 'created'];
  dataSource = new MatTableDataSource<any>(); 
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  actionsColumn = "actions"

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
          this.dataSource.data = data; 
          this.dataSource.paginator = this.paginator;
         }
      }
    )
  }

  editItem(element: any){
    console.log('Editing', element)
  }

  deleteItem(element: any){
    console.log('Delete', element)
  }

  userClick(row: any){
    this.communicationService.selectedUserId$.next(row.id)
  }
}
