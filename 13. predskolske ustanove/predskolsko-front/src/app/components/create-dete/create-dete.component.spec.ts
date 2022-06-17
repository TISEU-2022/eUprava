import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateDeteComponent } from './create-dete.component';

describe('CreateDeteComponent', () => {
  let component: CreateDeteComponent;
  let fixture: ComponentFixture<CreateDeteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateDeteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateDeteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
