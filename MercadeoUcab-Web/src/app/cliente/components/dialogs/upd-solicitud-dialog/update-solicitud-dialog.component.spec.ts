import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateSolicitudDialogComponent } from './update-solicitud-dialog.component';

describe('UpdateSolicitudDialogComponent', () => {
  let component: UpdateSolicitudDialogComponent;
  let fixture: ComponentFixture<UpdateSolicitudDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateSolicitudDialogComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateSolicitudDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
