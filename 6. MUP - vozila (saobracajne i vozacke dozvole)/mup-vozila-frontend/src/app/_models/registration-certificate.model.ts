import {Car} from "./car.model";

export class RegistrationCertificate {
  id?: any
  licensePlate?: string
  dayOfIssue?: string
  placeOfIssue?: string
  request?: boolean
  status?: boolean
  userId!: string
  carDTO!: Car
}
