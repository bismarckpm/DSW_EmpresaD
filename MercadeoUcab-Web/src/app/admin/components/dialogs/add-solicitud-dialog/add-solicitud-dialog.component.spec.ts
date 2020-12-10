import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSolicitudDialogComponent } from './add-solicitud-dialog.component';

describe('AddSolicitudDialogComponent', () => {
  let component: AddSolicitudDialogComponent;
  let fixture: ComponentFixture<AddSolicitudDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSolicitudDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSolicitudDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
