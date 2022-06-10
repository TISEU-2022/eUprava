import { ComponentFixture, TestBed } from '@angular/core/testing';
import { KonkursComponent } from './konkurs.component';

describe('KonkursComponent', () => {
  let component: KonkursComponent;
  let fixture: ComponentFixture<KonkursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KonkursComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KonkursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
