import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CashControlCreateComponent } from './cash-control-create.component';

describe('CashControlCreateComponent', () => {
  let component: CashControlCreateComponent;
  let fixture: ComponentFixture<CashControlCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CashControlCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CashControlCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
