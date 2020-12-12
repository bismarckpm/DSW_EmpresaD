import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdSubCatategoriaDialogComponent } from './upd-sub-catategoria-dialog.component';

describe('UpdSubCatategoriaDialogComponent', () => {
  let component: UpdSubCatategoriaDialogComponent;
  let fixture: ComponentFixture<UpdSubCatategoriaDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdSubCatategoriaDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdSubCatategoriaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
