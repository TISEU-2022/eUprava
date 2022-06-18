import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRegistrstionCertificatesComponent } from './view-registrstion-certificates.component';

describe('ViewRegistrstionCertificatesComponent', () => {
  let component: ViewRegistrstionCertificatesComponent;
  let fixture: ComponentFixture<ViewRegistrstionCertificatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewRegistrstionCertificatesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewRegistrstionCertificatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
