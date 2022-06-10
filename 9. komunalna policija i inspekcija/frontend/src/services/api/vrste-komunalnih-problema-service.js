import axios from "axios";

const getAll = () => {
    return axios
        .get("/vrsta-komunalnog-problema")
        .then(response => response.data)
}

const getById = (id) => {
    return axios
        .get(`/vrsta-komunalnog-problema/${id}`)
        .then(response => response.data)
}

const create = (data) => {
    return axios
        .post("/vrsta-komunalnog-problema", data)
        .then((response) => {
            return response.data;
        })
}

const update = (id, data) => {
    return axios
        .put(`/vrsta-komunalnog-problema/${id}`, data)
        .then((response) => {
            return response;
        })
}

const deleteById = (id) => {
    return axios
        .delete(`/vrsta-komunalnog-problema/${id}`)
        .then((response) => {
            return response;
        }).catch(err =>{
            alert("Nije moguće obrisati vrstu komunalnog problema!");
        })
}

const vrsteKomunalnihProblemaService = {
    getAll,
    getById,
    create,
    update,
    deleteById
}

export default vrsteKomunalnihProblemaService;