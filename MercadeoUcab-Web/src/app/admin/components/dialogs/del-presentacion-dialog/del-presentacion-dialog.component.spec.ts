import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DelPresentacionDialogComponent } from './del-presentacion-dialog.component';

describe('DelPresentacionDialogComponent', () => {
  let component: DelPresentacionDialogComponent;
  let fixture: ComponentFixture<DelPresentacionDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DelPresentacionDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DelPresentacionDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
