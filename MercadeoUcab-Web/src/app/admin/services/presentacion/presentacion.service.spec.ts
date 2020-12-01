/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { PresentacionService } from './presentacion.service';

describe('Service: Presentacion', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PresentacionService]
    });
  });

  it('should ...', inject([PresentacionService], (service: PresentacionService) => {
    expect(service).toBeTruthy();
  }));
});
