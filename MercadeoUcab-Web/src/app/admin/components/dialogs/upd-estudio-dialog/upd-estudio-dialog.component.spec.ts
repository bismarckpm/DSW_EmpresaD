import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdEstudioDialogComponent } from './upd-estudio-dialog.component';

describe('UpdEstudioDialogComponent', () => {
  let component: UpdEstudioDialogComponent;
  let fixture: ComponentFixture<UpdEstudioDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdEstudioDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdEstudioDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
