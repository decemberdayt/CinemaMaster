import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseSeatsComponent } from './purchase-seats.component';

describe('PurchaseSeatsComponent', () => {
  let component: PurchaseSeatsComponent;
  let fixture: ComponentFixture<PurchaseSeatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PurchaseSeatsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PurchaseSeatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
