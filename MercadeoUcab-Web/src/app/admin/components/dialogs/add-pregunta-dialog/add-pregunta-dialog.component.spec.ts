import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPreguntaDialogComponent } from './add-pregunta-dialog.component';

describe('AddPreguntaDialogComponent', () => {
  let component: AddPreguntaDialogComponent;
  let fixture: ComponentFixture<AddPreguntaDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPreguntaDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPreguntaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
