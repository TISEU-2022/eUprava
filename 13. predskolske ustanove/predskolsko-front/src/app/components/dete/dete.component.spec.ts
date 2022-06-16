import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeteComponent } from './dete.component';

describe('DeteComponent', () => {
  let component: DeteComponent;
  let fixture: ComponentFixture<DeteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
