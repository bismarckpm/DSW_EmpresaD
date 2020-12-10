/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { Muestra_poblacionService } from './muestra_poblacion.service';

describe('Service: Muestra_poblacion', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [Muestra_poblacionService]
    });
  });

  it('should ...', inject([Muestra_poblacionService], (service: Muestra_poblacionService) => {
    expect(service).toBeTruthy();
  }));
});
