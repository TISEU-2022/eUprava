import { TestBed } from '@angular/core/testing';

import { KonkursiService } from './konkursi.service';

describe('KonkursiService', () => {
  let service: KonkursiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KonkursiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
