import OglasService from "../services/OglasService";
import React from "react";

class ViewOglasComponent extends React.Component {
    
    constructor(props){
        super(props)

        this.state = {  
            id:this.props.match.params.id,
            oglasi: {}
        }
    }

    componentDidMount(){
        OglasService.getOglaseById(this.state.id).then(res => {
            this.setState({oglasi: res.data});
        });
    }
     
    render() { 
        return (
            <div>
                <div className="card col md-6 offset-md-3">
                    <p>AAA</p>
                </div>
            </div>
        );
    }
}
 
export default ViewOglasComponent;