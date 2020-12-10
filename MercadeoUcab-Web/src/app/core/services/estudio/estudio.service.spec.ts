/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { EstudioService } from './estudio.service';

describe('Service: Estudio', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EstudioService]
    });
  });

  it('should ...', inject([EstudioService], (service: EstudioService) => {
    expect(service).toBeTruthy();
  }));
});
