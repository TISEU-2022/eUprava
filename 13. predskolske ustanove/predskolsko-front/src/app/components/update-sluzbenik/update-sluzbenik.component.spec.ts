import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateSluzbenikComponent } from './update-sluzbenik.component';

describe('UpdateSluzbenikComponent', () => {
  let component: UpdateSluzbenikComponent;
  let fixture: ComponentFixture<UpdateSluzbenikComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateSluzbenikComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateSluzbenikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
