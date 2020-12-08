/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SubCategoriaSolicitudService } from './subCategoriaSolicitud.service';

describe('Service: SubCategoriaSolicitud', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SubCategoriaSolicitudService]
    });
  });

  it('should ...', inject([SubCategoriaSolicitudService], (service: SubCategoriaSolicitudService) => {
    expect(service).toBeTruthy();
  }));
});
