import { TestBed } from '@angular/core/testing';

import { EncuestadoServiceService } from './encuestado-service.service';

describe('EncuestadoServiceService', () => {
  let service: EncuestadoServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EncuestadoServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
