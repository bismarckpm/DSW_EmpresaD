/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { HijoService } from './hijo.service';

describe('Service: Hijo', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HijoService]
    });
  });

  it('should ...', inject([HijoService], (service: HijoService) => {
    expect(service).toBeTruthy();
  }));
});
