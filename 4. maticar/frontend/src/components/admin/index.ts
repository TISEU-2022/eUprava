export interface WorkerSchemaType {
    _id: string,
    username: string,
    firstName: string,
    lastName: string,
    identityNumber: string,
    password: string,
    roles: string[]
}

export interface WorkerProps {
    worker?: WorkerSchemaType
    onFinish: any
}