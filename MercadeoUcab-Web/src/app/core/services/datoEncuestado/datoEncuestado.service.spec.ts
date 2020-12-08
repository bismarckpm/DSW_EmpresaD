/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { DatoEncuestadoService } from './datoEncuestado.service';

describe('Service: DatoEncuestado', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DatoEncuestadoService]
    });
  });

  it('should ...', inject([DatoEncuestadoService], (service: DatoEncuestadoService) => {
    expect(service).toBeTruthy();
  }));
});
