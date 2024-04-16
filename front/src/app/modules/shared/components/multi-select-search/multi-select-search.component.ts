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
  displayedColumns: string[] = ['select', 'id', 'name'];
  dataSource: MatTableDataSource<ServiceModel>;
  selection: SelectionModel<ServiceModel>;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: MultiSelectModel,
    private dialogRef: MatDialogRef<MultiSelectSearchComponent> // Inject MatDialogRef
  ) {
    this.dataSource = new MatTableDataSource<ServiceModel>(data.valueList);
    this.selection = new SelectionModel<ServiceModel>(true, data.selectedList);
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

  masterToggle() {
    if (this.isAllSelected()) {
      this.selection.clear();
    } else {
      this.dataSource.data.forEach(row => this.selection.select(row));
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  closeDialogWithSelectedItems() {
    this.dialogRef.close(this.selection.selected.map(item => item));
  }
}
