/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { TipoSolicitudService } from './tipoSolicitud.service';

describe('Service: TipoSolicitud', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TipoSolicitudService]
    });
  });

  it('should ...', inject([TipoSolicitudService], (service: TipoSolicitudService) => {
    expect(service).toBeTruthy();
  }));
});
