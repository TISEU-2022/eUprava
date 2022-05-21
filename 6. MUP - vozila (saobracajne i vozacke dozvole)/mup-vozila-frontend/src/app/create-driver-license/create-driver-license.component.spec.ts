import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateDriverLicenseComponent } from './create-driver-license.component';

describe('CreateDriverLicenseComponent', () => {
  let component: CreateDriverLicenseComponent;
  let fixture: ComponentFixture<CreateDriverLicenseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateDriverLicenseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateDriverLicenseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
