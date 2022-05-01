import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollectJwtComponent } from './collect-jwt.component';

describe('CollectJwtComponent', () => {
  let component: CollectJwtComponent;
  let fixture: ComponentFixture<CollectJwtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CollectJwtComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CollectJwtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
