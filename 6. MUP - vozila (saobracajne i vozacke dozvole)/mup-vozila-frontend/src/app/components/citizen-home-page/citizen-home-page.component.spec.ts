import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CitizenHomePageComponent } from './citizen-home-page.component';

describe('CitizenHomePageComponent', () => {
  let component: CitizenHomePageComponent;
  let fixture: ComponentFixture<CitizenHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CitizenHomePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CitizenHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
