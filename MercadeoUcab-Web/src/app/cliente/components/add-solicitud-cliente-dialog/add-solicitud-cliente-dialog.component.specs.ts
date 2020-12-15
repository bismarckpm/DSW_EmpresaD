import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import {AddSolicitudClienteDialogComponent} from './add-solicitud-cliente-dialog.component';
describe('AddSolicitudClienteDialogComponent', () => {
  let component: AddSolicitudClienteDialogComponent;
  let fixture: ComponentFixture<AddSolicitudClienteDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSolicitudClienteDialogComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSolicitudClienteDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
