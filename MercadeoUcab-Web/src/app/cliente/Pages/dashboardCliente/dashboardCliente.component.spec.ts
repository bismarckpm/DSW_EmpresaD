import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { dashboardClienteComponent } from './dashboardCliente.component';

describe('dashboardClienteComponent', () => {
  let component: dashboardClienteComponent;
  let fixture: ComponentFixture<dashboardClienteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ dashboardClienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(dashboardClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
