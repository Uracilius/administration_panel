import { Component, ViewChild } from '@angular/core';
import { UsersApiService } from '../../services/users-api.service';
import { UserModuleCommunicationService } from '../../services/user-module-communication.service';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { DeleteConfirmationComponent } from '../../../shared/components/delete-confirmation/delete-confirmation.component';
import { of, switchMap } from 'rxjs';
import { UserFormComponent } from '../user-form/user-form.component';

@Component({
  selector: 'app-users-table',
  templateUrl: './users-table.component.html',
  styleUrl: './users-table.component.css'
})
export class UsersTableComponent {
  displayedColumns= ['login', 'description', 'status', 'created', 'actions'];
  dataSource = new MatTableDataSource<any>();
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  page =  0;
  pageSize = 10;
  selectedRow = -1;
  status = '';
  constructor(
    private usersApiService: UsersApiService,
    private communicationService: UserModuleCommunicationService,
    private matDialog: MatDialog,
  ) {}

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
    const dialogRef = this.matDialog.open(UserFormComponent, {
      width: '250px',
      data: { existingUser: element }
    });
  }

  deleteItem(element: any) {
    const dialogRef = this.matDialog.open(DeleteConfirmationComponent, {
      width: '250px',
    });
  
    dialogRef.afterClosed().pipe(
      switchMap(result => {
        if (result) {
          return this.usersApiService.deleteUser(element.id);
        } else {
          return of(null);
        }
      })
    ).subscribe(response => {
      if (response) {
        console.log('Deletion successful:', response);
      } else {
        console.log('Deletion cancelled or failed');
      }
    });
  }

  onPageChange(event: PageEvent) {
    this.page = event.pageIndex;
    this.pageSize = event.pageSize;
    this.populateTable();
  }

  userClick(row: any, index:number){
    this.communicationService.selectedUserId$.next(row.id);
    this.selectedRow=index;
  }
}
