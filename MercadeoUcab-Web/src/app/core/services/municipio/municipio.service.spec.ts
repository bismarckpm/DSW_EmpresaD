/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { MunicipioService } from './municipio.service';

describe('Service: Municipio', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MunicipioService]
    });
  });

  it('should ...', inject([MunicipioService], (service: MunicipioService) => {
    expect(service).toBeTruthy();
  }));
});
