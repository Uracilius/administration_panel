import { Component, ViewChild } from '@angular/core';
import { UsersApiService } from '../../services/users-api.service';
import { UserModuleCommunicationService } from '../../services/user-module-communication.service';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
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
  page =  0;
  pageSize = 10;
  constructor(
    private usersApiService: UsersApiService,
    private communicationService: UserModuleCommunicationService
  ) { }

  ngAfterViewInit() {
    if (this.paginator) {
      this.dataSource.paginator = this.paginator;
      this.page = this.paginator.pageIndex;
      this.pageSize = this.paginator.pageSize;
      this.populateTable();
    }
  }

  populateTable(){
    this.usersApiService.getUsersList(this.page, this.pageSize).subscribe(
      data => {
        if (data){
          this.dataSource.data = data;
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

  onPageChange(event: PageEvent) {
    this.page = event.pageIndex;
    this.pageSize = event.pageSize;
    this.populateTable();
  }


  userClick(row: any){
    this.communicationService.selectedUserId$.next(row.id)
  }
}
