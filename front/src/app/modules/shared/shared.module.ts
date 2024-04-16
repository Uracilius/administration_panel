import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditableItemsListComponent } from './components/editable-items-list/editable-items-list.component';
import { DeleteConfirmationComponent } from './components/delete-confirmation/delete-confirmation.component';
import { MatDialogModule } from '@angular/material/dialog';
import { RelationFormComponent } from './components/relation-form/relation-form.component';
import { MaterialModule } from '../../../assets/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { MultiSelectSearchComponent } from './components/multi-select-search/multi-select-search.component';


@NgModule({
  declarations: [
    EditableItemsListComponent,
    DeleteConfirmationComponent,
    RelationFormComponent,
    MultiSelectSearchComponent
  ],
  imports: [
    CommonModule,
    MatDialogModule,
    MaterialModule,
    ReactiveFormsModule
  ],
  exports:[
    EditableItemsListComponent,
    MatDialogModule
  ],
})
export class SharedModule { } 

