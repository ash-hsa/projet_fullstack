import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrendreRdvPatientComponent } from './prendre-rdv-patient.component';

describe('PrendreRdvPatientComponent', () => {
  let component: PrendreRdvPatientComponent;
  let fixture: ComponentFixture<PrendreRdvPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrendreRdvPatientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrendreRdvPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
