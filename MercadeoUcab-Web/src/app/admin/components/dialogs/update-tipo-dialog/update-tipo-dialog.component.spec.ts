import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateTipoDialogComponent } from './update-tipo-dialog.component';

describe('UpdateTipoDialogComponent', () => {
  let component: UpdateTipoDialogComponent;
  let fixture: ComponentFixture<UpdateTipoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateTipoDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateTipoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
