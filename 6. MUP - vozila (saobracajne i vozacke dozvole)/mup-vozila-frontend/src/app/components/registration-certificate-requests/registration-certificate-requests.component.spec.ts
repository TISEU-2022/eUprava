import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationCertificateRequestsComponent } from './registration-certificate-requests.component';

describe('RegistrationCertificateRequestsComponent', () => {
  let component: RegistrationCertificateRequestsComponent;
  let fixture: ComponentFixture<RegistrationCertificateRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationCertificateRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationCertificateRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
