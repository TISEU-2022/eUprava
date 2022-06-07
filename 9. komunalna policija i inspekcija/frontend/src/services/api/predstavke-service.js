import axios from "axios";

const getAll = () => {
    return axios
        .get("/predstavka")
        .then(response => response.data)
}

const getById = (id) => {
    return axios
        .get(`/predstavka/${id}`)
        .then(response => response.data)
}

const create = (data) => {

    let formData = new FormData();
    formData.append('naslov', data.naslov);
    formData.append('adresaDogadjaja', data.adresaDogadjaja);
    formData.append('datumDogadjaja', data.datumDogadjaja);
    formData.append('mestoDogadjaja', data.mestoDogadjaja);
    formData.append('vrstaPredstavkeId', data.vrstaPredstavke.id);
    formData.append('opis', data.opis);

    if(data.datoteke)
        formData.append('datoteke', data.datoteke);

    return axios
        .post("/predstavka", formData,{ headers: { "Content-type": "multipart/form-data" }})
        .then((response) => {
            return response.data;
        })
        .catch(error => console.log(error))
}

const predstavkeService = {
    getAll,
    getById,
    create
}

export default predstavkeService;