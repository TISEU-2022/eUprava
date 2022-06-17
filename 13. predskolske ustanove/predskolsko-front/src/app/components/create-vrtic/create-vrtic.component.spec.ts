import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateVrticComponent } from './create-vrtic.component';

describe('CreateVrticComponent', () => {
  let component: CreateVrticComponent;
  let fixture: ComponentFixture<CreateVrticComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateVrticComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateVrticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
