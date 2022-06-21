import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrivingLicenceEmployeePageComponent } from './driving-licence-employee-page.component';

describe('DrivingLicenceEmployeePageComponent', () => {
  let component: DrivingLicenceEmployeePageComponent;
  let fixture: ComponentFixture<DrivingLicenceEmployeePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DrivingLicenceEmployeePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DrivingLicenceEmployeePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
