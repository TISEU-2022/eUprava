import React from 'react';

const Input = (props) => {

    return (
        <div className="form-group my-3">
            <label htmlFor={props.title} className="form-control-label">{props.title}</label>
            <input id={props.title} className="form-control"
                   name={props.title} type={props.type}
                   value={props.value} onChange={(event => props.setValue(event.target.value))}/>
        </div>
    );
};

export default Input;