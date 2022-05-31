
const Select = (props) => {

    return (
        <div className="form-group my-3">
            <label htmlFor={props.title} className="form-control-label">{props.title}</label>
            <select id={props.title} className="form-control" name={props.title} value={props.value} onChange={event => props.setValue(event.target.value)}>
                {
                    props.options.map((option, index) => (
                        <option key={index} value={option.value}>{option.name}</option>
                    ))
                }
            </select>
        </div>
    );
};

export default Select;