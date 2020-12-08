/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { OpcionService } from './opcion.service';

describe('Service: Opcion', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OpcionService]
    });
  });

  it('should ...', inject([OpcionService], (service: OpcionService) => {
    expect(service).toBeTruthy();
  }));
});
