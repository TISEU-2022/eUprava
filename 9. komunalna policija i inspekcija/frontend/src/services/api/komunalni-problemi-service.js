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
    return axios
        .post("/komunalni-problem", data)
        .then((response) => {
            return response.data;
        })
}

const komunalniProblemiService = {
    getAll,
    getById,
    create
}

export default komunalniProblemiService;