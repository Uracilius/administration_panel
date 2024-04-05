import { Component } from '@angular/core';
import { UsersApiService } from '../../services/users-api.service';
import { ɵallowPreviousPlayerStylesMerge } from '@angular/animations/browser';

@Component({
  selector: 'app-permissions-table',
  templateUrl: './permissions-table.component.html',
  styleUrl: './permissions-table.component.css'
})
export class PermissionsTableComponent {
  displayedColumns= ['id', 'org_id', 'services', 'username', 'password'];
  dataSource = [{id:"hello",org_id:"world", services:"I am", username:"alive", password:"alive"}]
  constructor(
    private usersApiService: UsersApiService,
  ) { }
  ngOnInit() {
    this.populateTable();
  }

  populateTable(){
    this.usersApiService.getUsersTable(0, 10).subscribe(
      data => {
        if (data.code == 'OK'){
          console.log('data', data)
        }
      }
    )
  }
}
