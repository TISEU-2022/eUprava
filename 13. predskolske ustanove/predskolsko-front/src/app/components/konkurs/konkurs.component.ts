import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Konkurs } from 'src/app/model/Konkurs';
import { KonkursiService } from 'src/app/services/konkursi/konkursi.service';

@Component({
  selector: 'app-konkursi',
  templateUrl: './konkurs.component.html',
  styleUrls: ['./konkurs.component.css']
})
export class KonkursComponent implements OnInit {

  konkursi:Konkurs[] = [];

  constructor(private konkursService: KonkursiService, private router: Router) { }

  ngOnInit(): void {
    this.konkursService.getKonkurs().subscribe((konkursi) => {this.konkursi = konkursi;})
  }

  public createDete(id: number) {
    this.router.navigate(['createDete', id]);
  }

  public updateKonkurs(id:number){
    this.router.navigate(['updateKonkurs', id]);
  }

  public createKonkurs() {
    this.router.navigate(['createKonkurs']);
  }

  public blockKonkurs(id:number) {
    let response = this.konkursService.blockKonkurs(id);
    response.subscribe((konkursi)=> this.konkursi = konkursi)
  }

}
