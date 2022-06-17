import { CommonModule } from '@angular/common';
import { Component, NgModule, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Vrtic } from 'src/app/model/Vrtic';
import { VrticiService } from 'src/app/services/vrtici/vrtici.service';

@Component({
  selector: 'app-update-vrtic',
  templateUrl: './update-vrtic.component.html',
  styleUrls: ['./update-vrtic.component.css']
})
export class UpdateVrticComponent implements OnInit {

  
  id:number;
  vrtici: Vrtic;

  constructor(private route: ActivatedRoute,private vrticService: VrticiService, private router: Router) { }

  ngOnInit(): void {
    this.vrtici = new Vrtic();

    this.id = this.route.snapshot.params['id'];

    this.vrticService.getVerticById(this.id).subscribe(data=>{
      console.log(data)
      this.vrtici = data
    }, error => console.log(error));
  }

  redirectToListOfAllVrtici(){
    this.router.navigate(['/vrtici']);
  }

  updateVrtic(){
    this.vrticService.updateVrtic(this.id, this.vrtici).subscribe(data=>{
      console.log(data);
      this.vrtici = new Vrtic();
      this.redirectToListOfAllVrtici();
    }, error=>console.log(error));
  }
  onSubmit(){
    this.updateVrtic();
  }

 
}

