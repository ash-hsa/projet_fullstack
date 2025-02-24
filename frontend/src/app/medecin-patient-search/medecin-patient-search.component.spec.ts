import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedecinPatientSearchComponent } from './medecin-patient-search.component';

describe('MedecinPatientSearchComponent', () => {
  let component: MedecinPatientSearchComponent;
  let fixture: ComponentFixture<MedecinPatientSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedecinPatientSearchComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedecinPatientSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
