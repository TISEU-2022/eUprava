import { WorkerSchemaType } from "../components/admin";
import { apiAxios } from "./api";

export const getWorkers = () => {
    return apiAxios.get(`admin/workers`);
};

export const addEditWorker = (worker?: WorkerSchemaType, id?: string) => {
    let data = {
        identity_number: worker?.identityNumber,
        password: worker?.password,
        first_name: worker?.firstName,
        last_name: worker?.lastName,
        username: worker?.username
    }
    if (id) {
        return apiAxios.put(`/admin/workers/${id}`, data)
    }
    return apiAxios.post(`/admin/workers`, data)
}

export const deleteWorker = (id: string) => {
    return apiAxios.delete(`/admin/workers/${id}`)
}