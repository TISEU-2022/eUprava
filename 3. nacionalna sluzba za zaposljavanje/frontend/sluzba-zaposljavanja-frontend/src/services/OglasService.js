import httpClient from "../auth/JwtInterceptors";


const ADVERTISEMENTS_REST_API_URL = `${process.env.REACT_APP_APP_BACKEND_URL}/oglasi`;


export const getAllAdvertisements = async () => {
    try {
        const response = await httpClient.get(ADVERTISEMENTS_REST_API_URL);
        return response.data;
    } catch (error) {
        console.log(error);
    }
    
}

export const getAdvertisementById = async (id) => {
    await httpClient.get(`ADVERTISEMENTS_REST_API_URL/${id}`)
}
