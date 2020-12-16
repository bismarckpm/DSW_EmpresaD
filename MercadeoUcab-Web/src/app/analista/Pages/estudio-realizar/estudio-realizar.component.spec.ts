import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EstudioRealizarComponent } from './estudio-realizar.component';

describe('EstudioRealizarComponent', () => {
  let component: EstudioRealizarComponent;
  let fixture: ComponentFixture<EstudioRealizarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EstudioRealizarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EstudioRealizarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
