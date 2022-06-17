import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Dete } from 'src/app/model/Dete';
import { Konkurs } from 'src/app/model/Konkurs';
import { Vrtic } from 'src/app/model/Vrtic';
import { DeteService } from 'src/app/services/dete/dete.service';
import { KonkursiService } from 'src/app/services/konkursi/konkursi.service';
import { VrticiService } from 'src/app/services/vrtici/vrtici.service';

@Component({
  selector: 'app-create-dete',
  templateUrl: './create-dete.component.html',
  styleUrls: ['./create-dete.component.css']
})
export class CreateDeteComponent implements OnInit {

  id:number;
  dete: Dete;
  option: any;
  konkursi$: Observable<Konkurs[]>;
  vrtic: Observable<any[]>;

  constructor(private route: ActivatedRoute, private router: Router, private deteService: DeteService, private vrticService: VrticiService) { }

  ngOnInit(): void {
    this.dete = new Dete();
    this.id = this.route.snapshot.params['id'];
    this.vrtic = this.vrticService.getVerticByKonkursId(this.id);
  }
  redirectToListOfAllVrtici(){
    this.router.navigate(['/vrtici']);
  }

  createDete(){
    this.deteService.createDete(this.dete).subscribe(data=>{
      console.log(data);
      this.dete = new Dete();
      console.log(this.dete);
      this.redirectToListOfAllVrtici();
    }, error=>console.log(error));
  }
  onSubmit(){
    this.createDete();
  }

  onChange(option: any) {
    this.option = option;
  }

}
