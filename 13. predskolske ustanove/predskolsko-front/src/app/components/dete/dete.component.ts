import { CommonModule } from '@angular/common';
import { Component, NgModule, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dete } from 'src/app/model/Dete';
import { DeteService } from 'src/app/services/dete/dete.service';

@Component({
  selector: 'app-dete',
  templateUrl: './dete.component.html',
  styleUrls: ['./dete.component.css']
})
export class DeteComponent implements OnInit {
  deca: Dete[] = [];
  dete: any;
  user: any;

  constructor(private deteService: DeteService, private router: Router) { }

  ngOnInit(): void {
    this.deteService.getDeca().subscribe((deca) => this.deca = deca)
  }

  public createDete() {
    this.router.navigate(['createKonkurs']);
  }

  public blockDete(id:number) {
    let response = this.deteService.blockDete(id);
    response.subscribe((deca)=> this.deca = deca)
  }

}
