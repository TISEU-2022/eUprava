
const TextArea = (props) => {

    return (
        <div className="form-group my-3">
            <label htmlFor={props.title} className="form-control-label">{props.title}</label>
            <textarea id={props.title} className="form-control" style={{height: "250px"}}
                   name={props.title} value={props.value} onChange={(event => props.setValue(event.target.value))}/>
        </div>
    );
};

export default TextArea;