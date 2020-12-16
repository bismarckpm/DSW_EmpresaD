import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataTableClienteComponent } from '../data-table-cliente/data-table-cliente.component';

describe('DataTableClienteComponent', () => {
  let component: DataTableClienteComponent;
  let fixture: ComponentFixture<DataTableClienteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataTableClienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataTableClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
