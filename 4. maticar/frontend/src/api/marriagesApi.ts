import { apiAxios } from "./api";

export const addMarriage = (values: any) => {
    return apiAxios.post(`/worker/marriage`, {...values})
}

export const divorceMarriage = (marriage_id: string) => {
    return apiAxios.put(`/worker/marriage/${marriage_id}`)
}

export const getMarriages = () => {
    return apiAxios.get(`/worker/marriage`)
}