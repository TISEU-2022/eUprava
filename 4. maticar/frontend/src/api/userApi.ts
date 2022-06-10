import { apiAxios } from "./api";

export const getUserBirthCertificate = (idenNumber: string) => {
    return apiAxios.get(`/user/${idenNumber}`)
}