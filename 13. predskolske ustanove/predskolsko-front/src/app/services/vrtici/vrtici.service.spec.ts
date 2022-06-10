import { TestBed } from '@angular/core/testing';

import { VrticiService } from './vrtici.service';

describe('VrticiService', () => {
  let service: VrticiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VrticiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
