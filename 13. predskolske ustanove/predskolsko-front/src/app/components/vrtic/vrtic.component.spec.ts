import { ComponentFixture, TestBed } from '@angular/core/testing';
import { VrticComponent } from './vrtic.component';

describe('VrticComponent', () => {
  let component: VrticComponent;
  let fixture: ComponentFixture<VrticComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VrticComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VrticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
