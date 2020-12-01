/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SolicitudService } from './solicitud.service';

describe('Service: Solicitud', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SolicitudService]
    });
  });

  it('should ...', inject([SolicitudService], (service: SolicitudService) => {
    expect(service).toBeTruthy();
  }));
});
