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
    formData.append('ime', data.podnosilac.ime);
    formData.append('prezime', data.podnosilac.prezime);
    formData.append('jmbg', data.podnosilac.jmbg);
    formData.append('adresa', data.podnosilac.adresa);
    formData.append('mesto', data.podnosilac.mesto);
    formData.append('email', data.podnosilac.email);
    formData.append('telefon', data.podnosilac.telefon);
    formData.append('pttBroj', data.podnosilac.pttBroj);

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

const writeIzvestaj = (id, izvestajRequestDTO) =>{
    return axios
        .post(`/predstavka/izvestaj/${id}`, izvestajRequestDTO)
        .then(response=>{
            return response;
        })
}

const rejectIzvestaj = (id) =>{
    return axios
        .post(`/predstavka/odbaci-izvestaj/${id}`)
        .then(response =>{
            return response;
        })
}

const predstavkeService = {
    getAll,
    getById,
    create,
    writeIzvestaj,
    rejectIzvestaj
}

export default predstavkeService;