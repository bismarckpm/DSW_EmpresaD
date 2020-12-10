import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DelLugarDialogComponent } from './del-lugar-dialog.component';

describe('DelLugarDialogComponent', () => {
  let component: DelLugarDialogComponent;
  let fixture: ComponentFixture<DelLugarDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DelLugarDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DelLugarDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
