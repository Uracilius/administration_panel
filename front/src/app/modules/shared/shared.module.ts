import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditableItemsListComponent } from './components/editable-items-list/editable-items-list.component';



@NgModule({
  declarations: [
    EditableItemsListComponent
  ],
  imports: [
    CommonModule
  ],
  exports:[
    EditableItemsListComponent
  ]
})
export class SharedModule { } 
