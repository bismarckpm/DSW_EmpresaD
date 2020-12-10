import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteTipoDialogComponent } from './delete-tipo-dialog.component';

describe('DeleteTipoDialogComponent', () => {
  let component: DeleteTipoDialogComponent;
  let fixture: ComponentFixture<DeleteTipoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteTipoDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteTipoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
