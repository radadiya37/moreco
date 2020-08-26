import React from 'react';

import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Label, Alert, Row, Col } from 'reactstrap';
import { AvForm, AvField, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { Link } from 'react-router-dom';
import { Form } from 'react-bootstrap';

export interface ILoginModalProps {
  showModal: boolean;
  loginError: boolean;
  handleLogin: Function;
  handleClose: Function;
}

class LoginModal extends React.Component<ILoginModalProps> {
  handleSubmit = (event, errors, { username, password, rememberMe }) => {
    const { handleLogin } = this.props;
    handleLogin(username, password, rememberMe);
  };

  render() {
    const { loginError, handleClose } = this.props;

    return (
      <div>
        <div className="d-flex align-items-center auth px-0">
          <div className="row w-100 mx-0">
            <div className="col-lg-4 mx-auto">
              <div className="auth-form-light text-left py-5 px-4 px-sm-5">
                <div className="brand-logo">
                  <img src={"content/star_admin/images/logo.svg"} alt="logo" />
                </div>
                <h4>Hello! let&apos;s get started</h4>
                <h6 className="font-weight-light">Sign in to continue.</h6>
                <AvForm className="pt-3" onSubmit={this.handleSubmit}>
                  <Row>
                    <Col md="12">
                      {loginError ? (
                        <Alert color="danger">
                          <strong>Failed to sign in!</strong> Please check your credentials and try again.
                        </Alert>
                      ) : null}
                    </Col>
                    <Col md="12">
                      <AvField
                        name="username"
                        label="Username"
                        placeholder="Your username"
                        className="h-auto"
                        size="lg"
                        required
                        errorMessage="Username cannot be empty!"
                        autoFocus
                      />
                      <AvField
                        name="password"
                        type="password"
                        className="h-auto"
                        size="lg"
                        label="Password"
                        placeholder="Your password"
                        required
                        errorMessage="Password cannot be empty!"
                      />
                    </Col>
                  </Row>
                  <div className="mt-3">
                    <Button color="primary" className="btn-block btn-lg font-weight-medium auth-form-btn" type="submit">
                      Sign in
                  </Button>
                  </div>
                  <div className="my-2 d-flex justify-content-between align-items-center">
                    <div className="form-check">
                      <label className="form-check-label text-muted">
                        <AvInput type="checkbox" name="rememberMe" className="form-check-input" />
                        <i className="input-helper"></i>
                        Keep me signed in
                      </label>
                    </div>
                    <a href="!#" onClick={event => event.preventDefault()} className="auth-link text-black">Forgot password?</a>
                  </div>
                  <div className="text-center mt-4 font-weight-light">
                    Don&apos;t have an account? <Link to="/account/register" className="text-primary">Create</Link>
                  </div>
                </AvForm>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default LoginModal;
