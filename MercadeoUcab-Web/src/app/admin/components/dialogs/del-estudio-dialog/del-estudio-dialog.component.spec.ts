import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DelEstudioDialogComponent } from './del-estudio-dialog.component';

describe('DelEstudioDialogComponent', () => {
  let component: DelEstudioDialogComponent;
  let fixture: ComponentFixture<DelEstudioDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DelEstudioDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DelEstudioDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
