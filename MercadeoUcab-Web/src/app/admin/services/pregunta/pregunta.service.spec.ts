/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { PreguntaService } from './pregunta.service';

describe('Service: Pregunta', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PreguntaService]
    });
  });

  it('should ...', inject([PreguntaService], (service: PreguntaService) => {
    expect(service).toBeTruthy();
  }));
});
