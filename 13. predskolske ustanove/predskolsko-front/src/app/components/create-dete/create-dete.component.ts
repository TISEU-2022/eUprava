import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Dete } from 'src/app/model/Dete';
import { Vrtic } from 'src/app/model/Vrtic';
import { VrticiService } from 'src/app/services/vrtici/vrtici.service';

@Component({
  selector: 'app-create-dete',
  templateUrl: './create-dete.component.html',
  styleUrls: ['./create-dete.component.css']
})
export class CreateVrticComponent implements OnInit {

  id:number;
  dete: Dete;

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
