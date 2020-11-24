import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LandingAnalistaComponent } from './landing-analista.component';

describe('LandingAnalistaComponent', () => {
  let component: LandingAnalistaComponent;
  let fixture: ComponentFixture<LandingAnalistaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LandingAnalistaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LandingAnalistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
