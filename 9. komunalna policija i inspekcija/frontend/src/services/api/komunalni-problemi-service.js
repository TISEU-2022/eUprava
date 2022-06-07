import axios from "axios";

const getAll = () => {
    return axios
        .get("/komunalni-problem")
        .then(response => response.data)
}

const getById = (id) => {
    return axios
        .get(`/komunalni-problem/${id}`)
        .then(response => response.data)
}

const create = (data) => {

    let formData = new FormData();
    formData.append('adresaDogadjaja', data.adresaDogadjaja);
    formData.append('datumDogadjaja', data.datumDogadjaja);
    formData.append('mestoDogadjaja', data.mestoDogadjaja);
    formData.append('vrstaKomunalnogProblemaId', data.vrstaKomunalnogProblema.id);
    formData.append('opis', data.opis);

    if(data.datoteke)
        formData.append('datoteke', data.datoteke);

    return axios
        .post("/komunalni-problem", formData,{ headers: { "Content-type": "multipart/form-data" }})
        .then((response) => {
            return response.data;
        })
        .catch(error => console.log(error))
}

const komunalniProblemiService = {
    getAll,
    getById,
    create
}

export default komunalniProblemiService;