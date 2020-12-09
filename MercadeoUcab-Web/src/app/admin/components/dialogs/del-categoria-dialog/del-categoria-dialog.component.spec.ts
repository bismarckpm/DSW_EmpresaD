import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DelCategoriaDialogComponent } from './del-categoria-dialog.component';

describe('DelCategoriaDialogComponent', () => {
  let component: DelCategoriaDialogComponent;
  let fixture: ComponentFixture<DelCategoriaDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DelCategoriaDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DelCategoriaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
