import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateVrticComponent } from './update-vrtic.component';

describe('UpdateVrticComponent', () => {
  let component: UpdateVrticComponent;
  let fixture: ComponentFixture<UpdateVrticComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateVrticComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateVrticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
