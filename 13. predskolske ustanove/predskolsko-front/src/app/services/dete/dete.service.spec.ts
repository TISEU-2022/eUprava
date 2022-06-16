import { TestBed } from '@angular/core/testing';

import { DeteService } from './dete.service';

describe('DeteService', () => {
  let service: DeteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
