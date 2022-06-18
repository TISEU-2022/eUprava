import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Sluzbenik } from 'src/app/model/Sluzbenik';
import { SluzbenikService } from 'src/app/services/sluzbenik/sluzbenik.service';

@Component({
  selector: 'app-sluzbenik',
  templateUrl: './sluzbenik.component.html',
  styleUrls: ['./sluzbenik.component.css']
})
export class SluzbenikComponent implements OnInit {

  sluzbenici:Sluzbenik[] = [];

  constructor(private sluzbenikService: SluzbenikService, private router: Router) { }

  ngOnInit(): void {
    this.sluzbenikService.getSluzbenike().subscribe((sluzbenici) => {this.sluzbenici = sluzbenici;})
  }

  public updateSluzbenik(id:number){
    this.router.navigate(['updateSluzbenik', id]);
  }

  public createSluzbenik() {
    this.router.navigate(['createSluzbenik']);
  }

    public blockSluzbenik(id:number) {
    console.log(id);
    let response = this.sluzbenikService.blockSluzbenik(id);
    response.subscribe((sluzbenici)=> this.sluzbenici = sluzbenici)
  }

}
