import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuperAdminGestionAdminComponent } from './super-admin-gestion-admin.component';

describe('AdminMedecinsComponent', () => {
  let component: SuperAdminGestionAdminComponent;
  let fixture: ComponentFixture<SuperAdminGestionAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SuperAdminGestionAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuperAdminGestionAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
