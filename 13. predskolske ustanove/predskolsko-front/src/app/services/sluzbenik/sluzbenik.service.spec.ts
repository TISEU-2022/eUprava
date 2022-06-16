import { TestBed } from '@angular/core/testing';

import { SluzbenikService } from './sluzbenik.service';

describe('SluzbenikService', () => {
  let service: SluzbenikService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SluzbenikService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
