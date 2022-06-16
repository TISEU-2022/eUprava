import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SluzbenikComponent } from './sluzbenik.component';

describe('SluzbenikComponent', () => {
  let component: SluzbenikComponent;
  let fixture: ComponentFixture<SluzbenikComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SluzbenikComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SluzbenikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
