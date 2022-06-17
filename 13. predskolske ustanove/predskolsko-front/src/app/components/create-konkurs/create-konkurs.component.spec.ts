import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateKonkursComponent } from './create-konkurs.component';

describe('CreateKonkursComponent', () => {
  let component: CreateKonkursComponent;
  let fixture: ComponentFixture<CreateKonkursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateKonkursComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateKonkursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
