import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalistaOverviewComponent } from './analista-overview.component';

describe('AnalistaOverviewComponent', () => {
  let component: AnalistaOverviewComponent;
  let fixture: ComponentFixture<AnalistaOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalistaOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalistaOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
