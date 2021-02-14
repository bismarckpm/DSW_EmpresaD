import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EstudioResultadoDialogComponent } from './estudio-resultado-dialog.component';

describe('EstudioResultadoDialogComponent', () => {
  let component: EstudioResultadoDialogComponent;
  let fixture: ComponentFixture<EstudioResultadoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EstudioResultadoDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EstudioResultadoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
