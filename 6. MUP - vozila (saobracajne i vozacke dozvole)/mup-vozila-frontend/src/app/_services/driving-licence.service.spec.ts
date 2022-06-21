import { TestBed } from '@angular/core/testing';

import { DrivingLicenceService } from './driving-licence.service';

describe('DrivingLicenceService', () => {
  let service: DrivingLicenceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DrivingLicenceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
