import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarTelefonoComponent } from './agregar-telefono.component';

describe('AgregarTelefonoComponent', () => {
  let component: AgregarTelefonoComponent;
  let fixture: ComponentFixture<AgregarTelefonoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgregarTelefonoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgregarTelefonoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
