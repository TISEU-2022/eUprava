import axios from "axios";

const OGLASI_REST_API_URL = "http://localhost:8080/api/oglasi";

class OglasService{

    getOglase(){
        return axios.get(OGLASI_REST_API_URL);
    }

    getOglaseById(oglasId){
        return axios.getOglase(OGLASI_REST_API_URL + '/' + oglasId);
    }
}

export default new OglasService();