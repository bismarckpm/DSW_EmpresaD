import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdLugarDialogComponent } from './upd-lugar-dialog.component';

describe('UpdLugarDialogComponent', () => {
  let component: UpdLugarDialogComponent;
  let fixture: ComponentFixture<UpdLugarDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdLugarDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdLugarDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
