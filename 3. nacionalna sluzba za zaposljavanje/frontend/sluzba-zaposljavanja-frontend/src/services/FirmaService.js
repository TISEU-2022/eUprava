import httpClient from "../auth/JwtInterceptors";


const FIRM_REST_API_URL = `${process.env.REACT_APP_APP_BACKEND_URL}/firme`;

export const getAllFirms = async () => {
  try{
    const response = await httpClient.get(FIRM_REST_API_URL)
    return response.data
  }catch(err){
    console.log(err)
  }
}

export const getFirmById = async (id) => {
  try{
  const response = await httpClient.get(`http://localhost:3001/api/firme/${id}`)
  return response.data;

  } catch(error){
      console.log(error);
  }
}
