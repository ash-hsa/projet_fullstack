import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { VaccinationCenter } from '../vaccination-center';
import { FormsModule } from '@angular/forms';
import { DatePipe, NgIf, UpperCasePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { VaccinationService } from '../service/vaccination.service';

@Component({
  selector: 'app-vaccination-center',
  standalone: true,
  imports: [FormsModule,NgIf, DatePipe,UpperCasePipe],
  templateUrl: './vaccination-center.component.html',
  styleUrl: './vaccination-center.component.scss'
})
export class VaccinationCenterComponent implements OnInit{

  center?: VaccinationCenter;
  @Output() deleted = new EventEmitter<VaccinationCenter>();

  constructor(private route: ActivatedRoute, private vaccinationService: VaccinationService){}

  ngOnInit():void{
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.vaccinationService.getCenterById(id).subscribe(resultCenter=>{
      this.center=resultCenter;
    });
  }

  

  clearname() {
    this.center!.name="";
  }
  delete(){
    this.deleted.emit(this.center)
  }
  



  
}
