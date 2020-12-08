import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DelPreguntaDialogComponent } from './del-pregunta-dialog.component';

describe('DelPreguntaDialogComponent', () => {
  let component: DelPreguntaDialogComponent;
  let fixture: ComponentFixture<DelPreguntaDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DelPreguntaDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DelPreguntaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
