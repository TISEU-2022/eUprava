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
    return axios
        .post("/predstavka", data)
        .then((response) => {
            return response.data;
        })
}

const predstavkeService = {
    getAll,
    getById,
    create
}

export default predstavkeService;