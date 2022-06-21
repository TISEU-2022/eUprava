import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRegistrationCertificateComponent } from './view-registration-certificate.component';

describe('ViewRegistrationCertificateComponent', () => {
  let component: ViewRegistrationCertificateComponent;
  let fixture: ComponentFixture<ViewRegistrationCertificateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewRegistrationCertificateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewRegistrationCertificateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
