import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSluzbenikComponent } from './create-sluzbenik.component';

describe('CreateSluzbenikComponent', () => {
  let component: CreateSluzbenikComponent;
  let fixture: ComponentFixture<CreateSluzbenikComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateSluzbenikComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateSluzbenikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
