import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierProfilPatientComponent } from './modifier-profil-patient.component';

describe('ModifierProfilPatientComponent', () => {
  let component: ModifierProfilPatientComponent;
  let fixture: ComponentFixture<ModifierProfilPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModifierProfilPatientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModifierProfilPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
