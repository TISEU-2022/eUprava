import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Vrtic } from 'src/app/model/Vrtic';
import { VrticiService } from 'src/app/services/vrtici/vrtici.service';

@Component({
  selector: 'app-create-vrtic',
  templateUrl: './create-vrtic.component.html',
  styleUrls: ['./create-vrtic.component.css']
})
export class CreateVrticComponent implements OnInit {

  id:number;
  vrtic: Vrtic;

  constructor(private route: ActivatedRoute, private router: Router, private vrticService: VrticiService) { }

  ngOnInit(): void {
    this.vrtic = new Vrtic();
  }
  redirectToListOfAllVrtici(){
    this.router.navigate(['/vrtici']);
  }

  createVrtic(){
    this.vrticService.createVrtic(this.vrtic).subscribe(data=>{
      console.log(data);
      this.vrtic = new Vrtic();
      console.log(this.vrtic);
      this.redirectToListOfAllVrtici();
    }, error=>console.log(error));
  }
  onSubmit(){
    this.createVrtic();
  }

 

}
