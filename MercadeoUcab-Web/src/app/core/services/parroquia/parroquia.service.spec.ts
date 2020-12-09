/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ParroquiaService } from './parroquia.service';

describe('Service: Parroquia', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ParroquiaService]
    });
  });

  it('should ...', inject([ParroquiaService], (service: ParroquiaService) => {
    expect(service).toBeTruthy();
  }));
});
