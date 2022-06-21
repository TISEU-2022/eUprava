import React, {Component} from "react";
import {Image, Toast} from "react-bootstrap";
import greatSuccess from "../../pictures/greatSuccess.png";

export default class MyToast extends Component{

    render() {
        const toastCss={
            position:'fixed',
            top: '80px',
            right: '70px',
            zIndex:'1',
            boxShadow: '0 4px 8px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)'

        }

        return(
            <div style={this.props.children.show ? toastCss : null}>
                <Toast className={"border border-success bg-success text-white"} show={this.props.children.show}>
                    <Toast.Header className={"bg-success text-white"} closeButton={false}>
                        <strong className={"mr-auto"}>Успех</strong>

                    </Toast.Header>
                    <Toast.Body>
                        {this.props.children.message}
                        <Image className={"greatSuccess"} src={greatSuccess} thumbnail />
                    </Toast.Body>

                </Toast>
            </div>
        )
    }
}