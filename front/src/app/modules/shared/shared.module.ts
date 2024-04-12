import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditableItemsListComponent } from './components/editable-items-list/editable-items-list.component';
import { DeleteConfirmationComponent } from './components/delete-confirmation/delete-confirmation.component';
import { MatDialogModule } from '@angular/material/dialog';


@NgModule({
  declarations: [
    EditableItemsListComponent,
    DeleteConfirmationComponent
  ],
  imports: [
    CommonModule,
    MatDialogModule
  ],
  exports:[
    EditableItemsListComponent,
    MatDialogModule
  ],
})
export class SharedModule { } 

