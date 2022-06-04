import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrivingLicenceCitizenPageComponent } from './driving-licence-citizen-page.component';

describe('DrivingLicenceCitizenPageComponent', () => {
  let component: DrivingLicenceCitizenPageComponent;
  let fixture: ComponentFixture<DrivingLicenceCitizenPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DrivingLicenceCitizenPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DrivingLicenceCitizenPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
