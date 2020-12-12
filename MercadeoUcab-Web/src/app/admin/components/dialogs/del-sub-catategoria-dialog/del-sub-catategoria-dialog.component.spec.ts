import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DelSubCatategoriaDialogComponent } from './del-sub-catategoria-dialog.component';

describe('DelSubCatategoriaDialogComponent', () => {
  let component: DelSubCatategoriaDialogComponent;
  let fixture: ComponentFixture<DelSubCatategoriaDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DelSubCatategoriaDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DelSubCatategoriaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
