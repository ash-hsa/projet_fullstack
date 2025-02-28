import { Routes } from '@angular/router';
import { VaccinationCenterListComponent } from './vaccination-center-list/vaccination-center-list.component';
import { VaccinationCenterComponent } from './vaccination-center/vaccination-center.component';
import { LoginComponent } from './login/login.component';
import { AccueilComponent } from './accueil/accueil.component';
import { AccueilAdminComponent } from './accueil-admin/accueil-admin.component';
import { AccueilSuperAdminComponent } from './accueil-super-admin/accueil-super-admin.component';
import { AdminMedecinsComponent } from './admin-medecins/admin-medecins.component';
import { SuperAdminCentreComponent } from './super-admin-centre/super-admin-centre.component';
import { SuperAdminGestionAdminComponent } from './super-admin-gestion-admin/super-admin-gestion-admin.component';
import { AdminReservationsComponent } from './admin-reservations/admin-reservations.component';
import { AjouterMedecinComponent} from './ajout-medecin/ajout-medecin.component';
import { AccueilPatientComponent} from './accueil-patient/accueil-patient.component';
import { PrendreRdvPatientComponent} from './prendre-rdv-patient/prendre-rdv-patient.component';
import { ModifierProfilPatientComponent} from './modifier-profil-patient/modifier-profil-patient.component';


export const routes: Routes = [
    { path : "login", component: LoginComponent },
    { path: "centers", component: VaccinationCenterListComponent },
    { path : "centers/detail/:id", component: VaccinationCenterComponent },
    { path: "accueil", component: AccueilComponent },
    { path: "accueil-super-admin", component: AccueilSuperAdminComponent },
    { path: "accueil-admin", component: AccueilAdminComponent },
    { path: 'admin-medecins', component: AdminMedecinsComponent },
    { path: 'admin-reservations', component: AdminReservationsComponent },
    { path: 'super-admin-centre', component: SuperAdminCentreComponent },
    { path: 'super-admin-gestion-admin', component: SuperAdminGestionAdminComponent },
    { path: 'ajouter-medecin', component: AjouterMedecinComponent},
    { path: 'accueil-patient', component: AccueilPatientComponent},
    { path: 'prendre-rdv', component: PrendreRdvPatientComponent},
    { path: 'modifier-profil-patient', component: ModifierProfilPatientComponent},
    
    

    {path : "", redirectTo: "/login", pathMatch:"full"},
    
    
];
