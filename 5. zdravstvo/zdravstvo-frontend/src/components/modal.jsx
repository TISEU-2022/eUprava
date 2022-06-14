import React, { useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";

function ModalPopUp(props) {
  const [show, setShow] = useState(false);
  const [message, setMessage] = useState("");
  const [validated, setValidated] = useState(false);

  const handleClose = () => setShow(false);

  const handleShow = () => setShow(true);

  const handleSubmit = (event) => {
    event.preventDefault();
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.stopPropagation();
    }
    props.saveAppointmentReport({
      message: message,
      appointmentId: props.appointmentId,
    });
    setValidated(true);
  };

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Popuni izveštaj
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Izvještaj sa pregleda</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit} noValidate validated={validated}>
            <Form.Group
              className="mb-3"
              controlId="exampleForm.ControlTextarea1"
            >
              <Form.Control
                as="textarea"
                rows={3}
                value={message}
                onChange={(e) => setMessage(e.target.value)}
                required
              />
              <Form.Control.Feedback type="invalid">
                Popunite izveštaj
              </Form.Control.Feedback>
            </Form.Group>
            <Button variant="primary" type="submit">
              Sačuvaj
            </Button>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Zatvori
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default ModalPopUp;
