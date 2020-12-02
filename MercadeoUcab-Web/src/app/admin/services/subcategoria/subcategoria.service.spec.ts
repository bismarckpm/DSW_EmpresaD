/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SubcategoriaService } from './subcategoria.service';

describe('Service: Subcategoria', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SubcategoriaService]
    });
  });

  it('should ...', inject([SubcategoriaService], (service: SubcategoriaService) => {
    expect(service).toBeTruthy();
  }));
});
