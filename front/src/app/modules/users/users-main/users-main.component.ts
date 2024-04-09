import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserFormComponent } from '../components/user-form/user-form.component';

@Component({
  selector: 'app-users-main',
  templateUrl: './users-main.component.html',
  styleUrls: ['./users-main.component.css'] // Use styleUrls instead of styleUrl
})
export class UsersMainComponent {
  constructor(private dialog: MatDialog) { } // Removed 'private' from constructor parameters

  initializeUserForm(): void {
    const dialogRef = this.dialog.open(UserFormComponent, {
      width: '100%',
      height: '100%',
    });
  }
}
