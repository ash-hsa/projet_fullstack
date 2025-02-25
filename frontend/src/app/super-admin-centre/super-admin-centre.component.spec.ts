import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminMedecinsComponent } from './super-admin-centre.component';

describe('AdminMedecinsComponent', () => {
  let component: AdminMedecinsComponent;
  let fixture: ComponentFixture<AdminMedecinsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminMedecinsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminMedecinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
