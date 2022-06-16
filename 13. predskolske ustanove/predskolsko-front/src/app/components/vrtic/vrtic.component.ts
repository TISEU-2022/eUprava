import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Konkurs } from 'src/app/model/Konkurs';
import { Vrtic } from 'src/app/model/Vrtic';
import { KonkursiService } from 'src/app/services/konkursi/konkursi.service';
import { VrticiService } from 'src/app/services/vrtici/vrtici.service';

@Component({
  selector: 'app-vrtici',
  templateUrl: './vrtic.component.html',
  styleUrls: ['./vrtic.component.css']
})
export class VrticComponent implements OnInit {

  vrtici:Vrtic[] = [];

  constructor(private vrticService: VrticiService, private router: Router) { }

  ngOnInit(): void {
    this.vrticService.getVrtici().subscribe((vrtici) => {this.vrtici = vrtici;})
  }

  public updateVrtic(id:number){
    this.router.navigate(['updateVrtic', id]);
  }

  public createVrtic() {
    this.router.navigate(['createVrtic']);
  }

  public blockVrtic(id:number) {
    let response = this.vrticService.blockVrtic(id);
    response.subscribe((vrtici)=> this.vrtici = vrtici)
  }

}
