import httpClient from "../auth/JwtInterceptors";


const JOB_REST_API_URL = `${process.env.REACT_APP_APP_BACKEND_URL}/poslovi`;

export const getAllJobs = async () => {
  try{
    const response = await httpClient.get(JOB_REST_API_URL)
    return response.data
  }catch(err){
    console.log(err)
  }
}

export const getJobById = async (id) => {
  try{
  const response = await httpClient.get(`${JOB_REST_API_URL}/${id}`)
  return response.data;

  } catch(error){
      console.log(error);
  }
}