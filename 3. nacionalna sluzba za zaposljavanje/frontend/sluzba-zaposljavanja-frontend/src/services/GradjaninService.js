import axios from 'axios'

const GRADJANI_REST_API_URL = "http://localhost:8080/api/gradjani";

class GradjaninService{
    getGradjanine(){
        return axios.getGradjanine(GRADJANI_REST_API_URL);
    }
}

export default new GradjaninService();