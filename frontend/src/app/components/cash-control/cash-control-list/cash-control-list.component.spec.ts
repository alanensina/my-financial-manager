import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CashControlListComponent } from './cash-control-list.component';

describe('CashControlListComponent', () => {
  let component: CashControlListComponent;
  let fixture: ComponentFixture<CashControlListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CashControlListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CashControlListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
