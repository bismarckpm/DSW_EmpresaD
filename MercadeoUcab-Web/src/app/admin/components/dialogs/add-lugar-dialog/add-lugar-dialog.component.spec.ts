import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLugarDialogComponent } from './add-lugar-dialog.component';

describe('AddLugarDialogComponent', () => {
  let component: AddLugarDialogComponent;
  let fixture: ComponentFixture<AddLugarDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddLugarDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddLugarDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
