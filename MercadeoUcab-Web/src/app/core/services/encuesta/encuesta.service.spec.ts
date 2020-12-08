/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { EncuestaService } from './encuesta.service';

describe('Service: Encuesta', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EncuestaService]
    });
  });

  it('should ...', inject([EncuestaService], (service: EncuestaService) => {
    expect(service).toBeTruthy();
  }));
});
