import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Sluzbenik } from 'src/app/model/Sluzbenik';
import { SluzbenikService } from 'src/app/services/sluzbenik/sluzbenik.service';

@Component({
  selector: 'app-create-sluzbenik',
  templateUrl: './create-sluzbenik.component.html',
  styleUrls: ['./create-sluzbenik.component.css']
})
export class CreateSluzbenikComponent implements OnInit {

  id:number;
  sluzbenik: Sluzbenik;

  constructor( private route: ActivatedRoute, private router: Router, private sluzbenikService: SluzbenikService) { }

  ngOnInit(): void {
    this.sluzbenik = new Sluzbenik();
  }
  redirectToHome(){
    this.router.navigate(['/home']);
  }
  createSluzbenik(){
    this.sluzbenikService.createSluzbenik(this.sluzbenik).subscribe(data=>{
      console.log(data);
      this.sluzbenik = new Sluzbenik();
      console.log(this.sluzbenik);
      this.redirectToHome();
    },
    error=>console.log(error));
  }
  onSubmit(){
    this.createSluzbenik();
  }

}

