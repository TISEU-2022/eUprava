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
    try{
    const response = await httpClient.get(`${ADVERTISEMENTS_REST_API_URL}/${id}`)
    return response.data;

    } catch(error){
        console.log(error);
    }
}

export const addAdvertisement = async (object) => {
    try{
    const response = await httpClient.post(`${ADVERTISEMENTS_REST_API_URL}`, object)
    return response.data;

    } catch(error){
        console.log(error);
    }
}

export const updateAdvertisement = async (id, object) => {
    try{
    const response = await httpClient.put(`${ADVERTISEMENTS_REST_API_URL}/update/${id}`, object)
    return response.data;

    } catch(error){
        console.log(error);
    }
}