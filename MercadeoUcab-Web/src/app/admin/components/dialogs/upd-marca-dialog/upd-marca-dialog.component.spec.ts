import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdMarcaDialogComponent } from './upd-marca-dialog.component';

describe('UpdMarcaDialogComponent', () => {
  let component: UpdMarcaDialogComponent;
  let fixture: ComponentFixture<UpdMarcaDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdMarcaDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdMarcaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
