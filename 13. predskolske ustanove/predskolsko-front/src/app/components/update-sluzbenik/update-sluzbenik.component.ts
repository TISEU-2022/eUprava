import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Sluzbenik } from 'src/app/model/Sluzbenik';
import { SluzbenikService } from 'src/app/services/sluzbenik/sluzbenik.service';

@Component({
  selector: 'app-update-sluzbenik',
  templateUrl: './update-sluzbenik.component.html',
  styleUrls: ['./update-sluzbenik.component.css']
})
export class UpdateSluzbenikComponent implements OnInit {

  id:number;
  sluzbenici: Sluzbenik;

  constructor(private route: ActivatedRoute,private sluzbenikService: SluzbenikService, private router: Router) { }

  ngOnInit(): void {
    this.sluzbenici = new Sluzbenik();

    this.id = this.route.snapshot.params['id'];

    this.sluzbenikService.getSluzbenikById(this.id).subscribe(data=>{
      console.log(data)
      this.sluzbenici = data
    }, error => console.log(error));
  }

  redirectToListOfAllSluzbenici(){
    this.router.navigate(['/sluzbenici']);
  }

  updateSluzbenici(){
    this.sluzbenikService.updateSluzbenik(this.id, this.sluzbenici).subscribe(data=>{
      console.log(data);
      this.sluzbenici = new Sluzbenik();
      this.redirectToListOfAllSluzbenici();
    }, error=>console.log(error));
  }
  onSubmit(){
    this.updateSluzbenici();
  }

 
}
