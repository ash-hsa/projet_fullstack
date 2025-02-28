import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuperAdminCentreComponent } from './super-admin-centre.component';

describe('AdminMedecinsComponent', () => {
  let component: SuperAdminCentreComponent;
  let fixture: ComponentFixture<SuperAdminCentreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SuperAdminCentreComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuperAdminCentreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
