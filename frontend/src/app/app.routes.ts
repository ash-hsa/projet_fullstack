import { Routes } from '@angular/router';
import { VaccinationCenterListComponent } from './vaccination-center-list/vaccination-center-list.component';
import { VaccinationCenterComponent } from './vaccination-center/vaccination-center.component';
import { LoginComponent } from './login/login.component';
import { AccueilComponent } from './accueil/accueil.component';
import { AccueilAdminComponent } from './accueil-admin/accueil-admin.component';
import { AdminMedecinsComponent } from './admin-medecins/admin-medecins.component';
import { AdminReservationsComponent } from './admin-reservations/admin-reservations.component';
import { AjouterMedecinComponent} from './ajout-medecin/ajout-medecin.component';
import { AccueilPatientComponent} from './accueil-patient/accueil-patient.component';



export const routes: Routes = [
    {path : "login", component: LoginComponent},
    {path: "centers", component: VaccinationCenterListComponent},
    {path : "centers/detail/:id", component: VaccinationCenterComponent},
    {path: "accueil", component: AccueilComponent},
    {path: "accueil_admin", component: AccueilAdminComponent},
    { path: 'admin-medecins', component: AdminMedecinsComponent },
    { path: 'admin-reservations', component: AdminReservationsComponent },
    { path: 'ajouter-medecin', component: AjouterMedecinComponent},
    { path: 'accueil-patient', component: AccueilPatientComponent},

    {path : "", redirectTo: "/login", pathMatch:"full"},
    
    
];
