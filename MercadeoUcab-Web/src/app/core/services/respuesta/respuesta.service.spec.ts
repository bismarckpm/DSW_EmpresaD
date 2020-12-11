/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { RespuestaService } from './respuesta.service';

describe('Service: Respuesta', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RespuestaService]
    });
  });

  it('should ...', inject([RespuestaService], (service: RespuestaService) => {
    expect(service).toBeTruthy();
  }));
});
