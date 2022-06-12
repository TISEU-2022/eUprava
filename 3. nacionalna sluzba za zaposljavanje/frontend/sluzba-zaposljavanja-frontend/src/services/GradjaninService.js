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


