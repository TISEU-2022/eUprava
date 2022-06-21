import { DrivingLicence } from "./driving-licence";

export class DrivingLicenceChangeRequest {

  id!: number;
  requestType!: string;
  drivingLicenceDTO!: DrivingLicence;
  employeeId!: string
  requestStatus!: string;

}
