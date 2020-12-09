import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePreguntaDialogComponent } from './update-pregunta-dialog.component';

describe('UpdatePreguntaDialogComponent', () => {
  let component: UpdatePreguntaDialogComponent;
  let fixture: ComponentFixture<UpdatePreguntaDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdatePreguntaDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatePreguntaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
