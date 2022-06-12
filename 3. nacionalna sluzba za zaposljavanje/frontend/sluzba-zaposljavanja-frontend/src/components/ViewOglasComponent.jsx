import {getAdvertisementById} from "../services/OglasService";
import React, { useEffect, useState } from "react";

const ViewOglasComponent = (id) => {
    const [oglas, setOglas] = useState();

    useEffect(() => {
        const oglas = getAdvertisementById(id);
        setOglas(oglas);
    }, []);

        return (
            <div>
                <div className="card col md-6 offset-md-3">
                    <p>AAA</p>
                </div>
            </div>
        );
    }

 
export default ViewOglasComponent;