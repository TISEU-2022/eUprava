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
    formData.append('ime', data.podnosilac.ime);
    formData.append('prezime', data.podnosilac.prezime);
    formData.append('jmbg', data.podnosilac.jmbg);
    formData.append('adresa', data.podnosilac.adresa);
    formData.append('mesto', data.podnosilac.mesto);
    formData.append('email', data.podnosilac.email);
    formData.append('telefon', data.podnosilac.telefon);
    formData.append('pttBroj', data.podnosilac.pttBroj);
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
            return response;
        }).catch(error =>{
            if(error.response.status === 404){
                alert("Maticar: Uneli ste netacan JMBG.");
            }else if(error.response.status === 405){
                alert("Maticar: Unesite JMBG.");
            }else{
                alert("Greska nije poznata.");
            }
        })
}

const writeIzvestaj = (id, izvestajRequestDTO) =>{
    return axios
        .post(`/komunalni-problem/izvestaj/${id}`, izvestajRequestDTO)
        .then(response=>{
            return response;
        })
}

const rejectIzvestaj = (id) =>{
    return axios
        .post(`/komunalni-problem/odbaci-izvestaj/${id}`)
        .then(response =>{
            return response;
        })
}

const komunalniProblemiService = {
    getAll,
    getById,
    create,
    writeIzvestaj,
    rejectIzvestaj
}

export default komunalniProblemiService;