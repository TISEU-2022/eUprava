import axios from "axios";

const getAll = () => {
    return axios
        .get("/vrsta-predstavke")
        .then(response => response.data)
}

const getById = (id) => {
    return axios
        .get(`/vrsta-predstavke/${id}`)
        .then(response => response.data)
}

const create = (data) => {
    return axios
        .post("/vrsta-predstavke", data)
        .then((response) => {
            return response.data;
        })
}

const update = (id, data) => {
    return axios
        .put(`/vrsta-predstavke/${id}`, data)
        .then((response) => {
            return response;
        })
}

const deleteById = (id) => {
    return axios
        .delete(`/vrsta-predstavke/${id}`)
        .then((response) => {
            return response;
        })
}

const vrstePredstavkiService = {
    getAll,
    getById,
    create,
    update,
    deleteById
}

export default vrstePredstavkiService;