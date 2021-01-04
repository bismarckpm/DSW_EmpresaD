import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddClienteDialogComponent } from './add-cliente-dialog.component';

describe('AddClienteDialogComponent', () => {
  let component: AddClienteDialogComponent;
  let fixture: ComponentFixture<AddClienteDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddClienteDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddClienteDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
