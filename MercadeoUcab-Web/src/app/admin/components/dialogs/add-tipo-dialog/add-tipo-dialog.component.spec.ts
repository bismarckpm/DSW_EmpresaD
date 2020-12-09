import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTipoDialogComponent } from './add-tipo-dialog.component';

describe('AddTipoDialogComponent', () => {
  let component: AddTipoDialogComponent;
  let fixture: ComponentFixture<AddTipoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddTipoDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTipoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
