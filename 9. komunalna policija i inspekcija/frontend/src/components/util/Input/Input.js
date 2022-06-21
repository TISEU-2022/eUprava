
const Input = (props) => {

    return (
        <div className="form-group my-3">
            <label htmlFor={props.title} className="form-control-label">{props.title}</label>
            <input id={props.title} className="form-control"
                   name={props.title} type={props.type}
                   value={props.value} onChange={(event => {
                       if(props.type === "file") {
                           const file = event.target.files[0];

                           let reader = new FileReader();
                           reader.readAsDataURL(file);
                           reader.onload = function () {
                               props.setValue(reader.result);
                           };
                           reader.onerror = function (error) {
                               console.log('Error: ', error);
                           };
                       } else {
                           props.setValue(event.target.value)
                       }
            })}/>
        </div>
    );
};

export default Input;