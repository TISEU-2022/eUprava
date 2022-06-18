import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateRegistrationCertificateComponent } from './create-registration-certificate.component';

describe('CreateRegistrationCertificateComponent', () => {
  let component: CreateRegistrationCertificateComponent;
  let fixture: ComponentFixture<CreateRegistrationCertificateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateRegistrationCertificateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateRegistrationCertificateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
