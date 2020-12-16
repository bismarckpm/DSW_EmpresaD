import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DelMarcaDialogComponent } from './del-marca-dialog.component';

describe('DelMarcaDialogComponent', () => {
  let component: DelMarcaDialogComponent;
  let fixture: ComponentFixture<DelMarcaDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DelMarcaDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DelMarcaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
