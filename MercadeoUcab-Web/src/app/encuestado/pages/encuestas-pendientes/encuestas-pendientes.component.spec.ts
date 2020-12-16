import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EncuestasPendientesComponent } from './encuestas-pendientes.component';

describe('EncuestasPendientesComponent', () => {
  let component: EncuestasPendientesComponent;
  let fixture: ComponentFixture<EncuestasPendientesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EncuestasPendientesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EncuestasPendientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
