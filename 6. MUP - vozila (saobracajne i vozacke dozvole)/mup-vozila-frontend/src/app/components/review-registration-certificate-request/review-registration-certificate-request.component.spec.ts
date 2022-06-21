import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewRegistrationCertificateRequestComponent } from './review-registration-certificate-request.component';

describe('ReviewRegistrationCertificateRequestComponent', () => {
  let component: ReviewRegistrationCertificateRequestComponent;
  let fixture: ComponentFixture<ReviewRegistrationCertificateRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewRegistrationCertificateRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewRegistrationCertificateRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
