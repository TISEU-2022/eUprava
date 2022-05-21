import { DrivingLicence } from "./driving-licence";

export class RequestForDrivingLicence {

  id!: number;
  drivingLicenceType!: string;
  citizenId!: string;
  employeeId!: string;
  drivingLicenceDTO!: DrivingLicence;
  requestStatus!: string;

}
