import { Component } from '@angular/core';
import { VaccinationCenter } from '../vaccination-center';
import { DatePipe, NgClass, NgFor, NgIf, UpperCasePipe } from '@angular/common';
import { VaccinationCenterComponent } from "../vaccination-center/vaccination-center.component";
import { VaccinationService } from '../service/vaccination.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-vaccination-center-list',
  standalone: true,
  imports: [NgFor, NgIf, NgClass, DatePipe, UpperCasePipe, VaccinationCenterComponent,RouterLink],
  templateUrl: './vaccination-center-list.component.html',
  styleUrl: './vaccination-center-list.component.scss'
})
export class VaccinationCenterListComponent {
  
  centers!:VaccinationCenter[];
  constructor(private service : VaccinationService) {}

  selectedCenter?: VaccinationCenter;

  select(center:VaccinationCenter){
    this.selectedCenter=center;
  }

  ngOnInit() : void{
    this.service.getAllVaccinationCenter().subscribe(resultCenters=>{
      this.centers=resultCenters;
    });
  }

  onDelete(center:VaccinationCenter){
    delete this.selectedCenter;
    this.centers.splice(this.centers.indexOf(center),1)
  }

}
