import httpClient from "../auth/JwtInterceptors";


const KONKURS_REST_API_URL = `${process.env.REACT_APP_APP_BACKEND_URL}/konkursi`;


export const getAllKonkurse = async () => {
    try {
        const response = await httpClient.get(KONKURS_REST_API_URL);
        return response.data;
    } catch (error) {
        console.log(error);
    }
    
}

/*export const getAdvertisementById = async (id) => {
    try{
    const response = await httpClient.get(`${ADVERTISEMENTS_REST_API_URL}/${id}`)
    return response.data;

    } catch(error){
        console.log(error);
    }
}*/

export const addKonkurs = async (object) => {
    try{
    const response = await httpClient.post(`${KONKURS_REST_API_URL}`, object)
    return response.data;

    } catch(error){
        console.log(error);
    }
}
