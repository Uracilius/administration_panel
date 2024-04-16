import { AfterViewInit, Component, OnInit, ViewChild, Inject } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { SelectionModel } from '@angular/cdk/collections';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog'; // Import MatDialogRef
import { ServiceModel } from '../../model/service-model';
import { MultiSelectModel } from '../../model/multi-select-model';

@Component({
  selector: 'app-multi-select',
  templateUrl: './multi-select-search.component.html',
  styleUrls: ['./multi-select-search.component.css']
})
export class MultiSelectSearchComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  displayedColumns: string[] = ['select', 'name', 'description'];
  dataSource!: MatTableDataSource<any>;
  selection = new SelectionModel<number>(true, []); 

  

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: MultiSelectModel,
    private dialogRef: MatDialogRef<MultiSelectSearchComponent> 
  ) {
    this.dataSource = new MatTableDataSource<any>(this.data.valueList);
    this.selection = new SelectionModel<number>(true, this.data.selectedList.map(a=>a.id));
  }

  ngOnInit() {
    this.dataSource.data = this.data.valueList;
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  closeDialogWithSelectedItems() {
    this.dialogRef.close(this.selection.selected.map(item => item));
  }

  masterToggle() {
    if (this.isAllSelected()) {
      this.selection.clear();
    } else {
      this.dataSource.data.forEach(row => this.selection.select(row.id));
    }
  }

  toggleSelection(row: ServiceModel) {
    this.selection.toggle(row.id);
  }

  isSelected(row: ServiceModel) {
    return this.selection.isSelected(row.id);
  }
}
