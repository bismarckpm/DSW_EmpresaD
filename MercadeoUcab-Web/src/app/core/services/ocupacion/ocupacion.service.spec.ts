/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { OcupacionService } from './ocupacion.service';

describe('Service: Ocupacion', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OcupacionService]
    });
  });

  it('should ...', inject([OcupacionService], (service: OcupacionService) => {
    expect(service).toBeTruthy();
  }));
});
