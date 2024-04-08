import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditableItemsListComponent } from './editable-items-list.component';

describe('EditableItemsListComponent', () => {
  let component: EditableItemsListComponent;
  let fixture: ComponentFixture<EditableItemsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditableItemsListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditableItemsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
