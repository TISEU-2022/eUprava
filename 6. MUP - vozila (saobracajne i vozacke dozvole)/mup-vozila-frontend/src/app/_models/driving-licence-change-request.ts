import { DrivingLicence } from "./driving-licence";

export class DrivingLicenceChangeRequest {

  id!: number;
  requestType!: string;
  drivingLicence!: DrivingLicence;
  employeeId!: string
  requestStatus!: string;

}
