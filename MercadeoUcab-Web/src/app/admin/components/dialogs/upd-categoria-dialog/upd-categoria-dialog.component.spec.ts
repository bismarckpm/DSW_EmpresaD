import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdCategoriaDialogComponent } from './upd-categoria-dialog.component';

describe('UpdCategoriaDialogComponent', () => {
  let component: UpdCategoriaDialogComponent;
  let fixture: ComponentFixture<UpdCategoriaDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdCategoriaDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdCategoriaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
