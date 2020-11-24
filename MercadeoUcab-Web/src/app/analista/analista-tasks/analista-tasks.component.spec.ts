import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalistaTasksComponent } from './analista-tasks.component';

describe('AnalistaTasksComponent', () => {
  let component: AnalistaTasksComponent;
  let fixture: ComponentFixture<AnalistaTasksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalistaTasksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalistaTasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
