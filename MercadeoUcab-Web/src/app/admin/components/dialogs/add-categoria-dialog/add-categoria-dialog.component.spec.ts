import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCategoriaDialogComponent } from './add-categoria-dialog.component';

describe('AddCategoriaDialogComponent', () => {
  let component: AddCategoriaDialogComponent;
  let fixture: ComponentFixture<AddCategoriaDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCategoriaDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCategoriaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
