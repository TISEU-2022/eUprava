import { useEffect, useState } from "react"
import Header from "../navbar/Header"
import axios from 'axios';
import { useJwt } from "react-jwt";
import { useNavigate } from 'react-router-dom';
import RegistrationBackend from "../auth/RegistrationBackend";
import ZakazivanjeForma from "../forms/ZakazivanjeForma";


export const ZakazivanjeLicna = () => {

    const navigate = useNavigate();
    const usertoken = localStorage.getItem("token")
    const { decodedToken, isExpired } = useJwt(usertoken);

    const [show, setShow] = useState(false);
    const [docType, setDocumentType] = useState("DOCUMENT_IDCARD")
    const [minor, setMinor] = useState(false)

    useEffect(() => {

        if (decodedToken) {
            axios.post("http://localhost:11001/api/appointment/initialRequest", {
                username: decodedToken.username
            })
                .then((res) => {
                    if (res.data == true) {
                        setShow(true)
                    } else if (res.data == false) {
                        setShow(false)
                    }
                })
                .catch((err) => {
                    console.log(err)

                })
        }
    }, [decodedToken])


    return (
        <>
            <Header />
            {show && <RegistrationBackend showModal={show} />}

            <ZakazivanjeForma docType={docType} minor={minor}/>

        </>
    )
}

export default ZakazivanjeLicna