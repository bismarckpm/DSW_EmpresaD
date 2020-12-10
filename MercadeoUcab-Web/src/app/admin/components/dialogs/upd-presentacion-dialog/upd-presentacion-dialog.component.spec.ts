import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdPresentacionDialogComponent } from './upd-presentacion-dialog.component';

describe('UpdPresentacionDialogComponent', () => {
  let component: UpdPresentacionDialogComponent;
  let fixture: ComponentFixture<UpdPresentacionDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdPresentacionDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdPresentacionDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
