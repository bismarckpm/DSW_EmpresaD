import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroDatoEncuestadoComponent } from './registro-dato-encuestado.component';

describe('RegistroDatoEncuestadoComponent', () => {
  let component: RegistroDatoEncuestadoComponent;
  let fixture: ComponentFixture<RegistroDatoEncuestadoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistroDatoEncuestadoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistroDatoEncuestadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
