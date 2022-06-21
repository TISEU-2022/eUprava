import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationCertificateRequestComponent } from './registration-certificate-request.component';

describe('RegistrationCertificateRequestComponent', () => {
  let component: RegistrationCertificateRequestComponent;
  let fixture: ComponentFixture<RegistrationCertificateRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationCertificateRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationCertificateRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
