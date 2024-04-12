import { Component, ViewChild } from '@angular/core';
import { UsersApiService } from '../../services/users-api.service';
import { UserModuleCommunicationService } from '../../services/user-module-communication.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-user-services-table',
  templateUrl: './user-services-table.component.html',
  styleUrl: './user-services-table.component.css'
})
export class UserServicesTableComponent {
  displayedColumns= ['code', 'name', 'description', 'actions'];
  dataSource = new MatTableDataSource<any>(); 
  @ViewChild(MatPaginator) paginator!: MatPaginator;
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

  deleteItem(element: any){
    console.log('Trying to delete')
  }

  populateTable(userId: number){
    this.usersApiService.getUserServicesList(userId).subscribe(
      data => {
        if (data){
          this.dataSource.data = data; 
          this.dataSource.paginator = this.paginator;
        }
      }
    )
  }
  userClick(row: any){
    console.log('user clicked', row)
  }
}
