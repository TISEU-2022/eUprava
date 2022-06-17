import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Konkurs } from 'src/app/model/Konkurs';
import { Vrtic } from 'src/app/model/Vrtic';
import { KonkursiService } from 'src/app/services/konkursi/konkursi.service';
import { VrticiService } from 'src/app/services/vrtici/vrtici.service';

@Component({
  selector: 'app-create-konkurs',
  templateUrl: './create-konkurs.component.html',
  styleUrls: ['./create-konkurs.component.css']
})
export class CreateKonkursComponent implements OnInit {

  id:number;
  vrtic: Vrtic;
  option: any;
  konkurs: Konkurs;

  vrtici$ : Observable<Vrtic[]>;

  constructor(private route: ActivatedRoute, private router: Router, private konkursService: KonkursiService, private vrticService: VrticiService) { }

  ngOnInit(): void {
    this.konkurs = new Konkurs();
    this.vrtici$ = this.vrticService.getVrtici();
  }
  redirectToListOfAllKonkursi(){
    this.router.navigate(['/konkurs']);
  }

  createSemester(){
    this.konkursService.createKonkurs(this.konkurs).subscribe(data=>{
      console.log(this.option);
      this.konkurs = new Konkurs();
      this.redirectToListOfAllKonkursi();
    }, error=>console.log(error));
  }
  onSubmit(){
    this.createSemester();
  }

  onChange(option: any) {
    this.option = option;
  }

}
