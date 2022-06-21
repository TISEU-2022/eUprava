import { TestBed } from '@angular/core/testing';

import { RegistrationCertificateService } from './registration-certificate.service';

describe('RegistrationCertificateServiceService', () => {
  let service: RegistrationCertificateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegistrationCertificateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
