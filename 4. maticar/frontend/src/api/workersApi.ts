import { WorkerSchemaType } from "../components/admin";
import { apiAxios } from "./api";

export const getWorkers = () => {
    return apiAxios.get(`admin/workers`);
};

export const addEditWorker = (worker?: WorkerSchemaType, id?: string) => {
    let data: any = {
        identity_number: worker?.identityNumber,
        first_name: worker?.firstName,
        last_name: worker?.lastName,
        username: worker?.username,
        roles: ["maticar_worker"]
    }
    if (worker?.password) {
        data['password'] = worker?.password
    }
    if (id) {
        return apiAxios.put(`/admin/workers/${id}`, data)
    }
    return apiAxios.post(`/admin/workers`, data)
}

export const deleteWorker = (id: string) => {
    return apiAxios.delete(`/admin/workers/${id}`)
}