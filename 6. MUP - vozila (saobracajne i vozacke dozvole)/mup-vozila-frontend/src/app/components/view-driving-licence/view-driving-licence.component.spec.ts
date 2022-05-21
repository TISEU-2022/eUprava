import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDrivingLicenceComponent } from './view-driving-licence.component';

describe('ViewDrivingLicenceComponent', () => {
  let component: ViewDrivingLicenceComponent;
  let fixture: ComponentFixture<ViewDrivingLicenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewDrivingLicenceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewDrivingLicenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
