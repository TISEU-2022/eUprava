import httpClient from "../auth/JwtInterceptors";


const CITIZEN_REST_API_URL = `${process.env.REACT_APP_APP_BACKEND_URL}/gradjani`;

export async function getAllCitizen() {
    try{
        const response = await httpClient.get(CITIZEN_REST_API_URL);
        return response.data
    }catch(err){
        console.log(err);
    }


    
}

export const getCitizenByusername = async (username) => {
    try{
    const response = await httpClient.get(`${CITIZEN_REST_API_URL}/username/${username}`)
    return response.data;

    } catch(error){
        console.log(error);
    }
}

export const getBirthCertificate = async (jmbg) => {
    try{
    const response = await httpClient.get(`${CITIZEN_REST_API_URL}/info/${jmbg}`)
    return response.data;

    } catch(error){
        console.log(error);
    }
}


