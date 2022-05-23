import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DrivingLicence } from '../../_models/driving-licence';
import { DrivingLicenceService } from '../../_services/driving-licence.service';

@Component({
  selector: 'app-view-driving-licence',
  templateUrl: './view-driving-licence.component.html',
  styleUrls: ['./view-driving-licence.component.css']
})
export class ViewDrivingLicenceComponent implements OnInit {
  drivingLicence: DrivingLicence = new DrivingLicence();

  constructor(private route: ActivatedRoute,
    private drivingLicenceService: DrivingLicenceService) { }

  ngOnInit(): void {

    const id = this.route.snapshot.params['id'];
    this.drivingLicenceService.getDrivingLicenceById(id).subscribe(
      data => {
        this.drivingLicence = data;
      }
    )

  }

}
