import { Component, Input } from '@angular/core';
@Component({
  selector: 'app-editable-items-list',
  templateUrl: './editable-items-list.component.html',
  styleUrl: './editable-items-list.component.css'
})
export class EditableItemsListComponent {

  constructor() { }

  @Input() itemDisplayList: any[] = [];
  @Input() itemHoverInfo: any[] = [];
  selectedRowIndex = -1;

  rowClick(index: number){
    console.log('row clicked',index )
  }

  onHover(index: number){
    console.log('hovered over', index)
  }
}
