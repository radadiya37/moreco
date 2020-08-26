import React, { useState, useEffect } from 'react';

import { connect } from 'react-redux';
import { AvForm, AvField } from 'availity-reactstrap-validation';
import { Row, Col, Alert, Button } from 'reactstrap';

import PasswordStrengthBar from 'app/shared/layout/password/password-strength-bar';
import { handleRegister, reset } from './register.reducer';
import { Link } from 'react-router-dom';

export type IRegisterProps = DispatchProps;

export const RegisterPage = (props: IRegisterProps) => {
  const [password, setPassword] = useState('');

  useEffect(() => () => props.reset(), []);

  const handleValidSubmit = (event, values) => {
    props.handleRegister(values.username, values.email, values.firstPassword);
    event.preventDefault();
  };

  const updatePassword = event => setPassword(event.target.value);

  return (
    <div>
      <div className="d-flex align-items-center auth px-0">
        <div className="row w-100 mx-0">
          <div className="col-lg-4 mx-auto">
            <div className="auth-form-light text-left py-5 px-4 px-sm-5">
              <div className="brand-logo">
                <img src={"content/star_admin/images/logo.svg"} alt="logo" />
              </div>
              <h4>New here?</h4>
              <h6 className="font-weight-light">Signing up is easy. It only takes a few steps</h6>
              <AvForm id="register-form" className="pt-3" onValidSubmit={handleValidSubmit}>
                <div className="form-group">
                  <AvField
                    name="username"
                    className="form-control form-control-lg"
                    label="Username"
                    placeholder={'Your username'}
                    validate={{
                      required: { value: true, errorMessage: 'Your username is required.' },
                      pattern: { value: '^[_.@A-Za-z0-9-]*$', errorMessage: 'Your username can only contain letters and digits.' },
                      minLength: { value: 1, errorMessage: 'Your username is required to be at least 1 character.' },
                      maxLength: { value: 50, errorMessage: 'Your username cannot be longer than 50 characters.' }
                    }}
                  />
                </div>
                <div className="form-group">
                  <AvField
                    name="email"
                    label="Email"
                    className="form-control form-control-lg"
                    placeholder={'Your email'}
                    type="email"
                    validate={{
                      required: { value: true, errorMessage: 'Your email is required.' },
                      minLength: { value: 5, errorMessage: 'Your email is required to be at least 5 characters.' },
                      maxLength: { value: 254, errorMessage: 'Your email cannot be longer than 50 characters.' }
                    }}
                  />
                </div>
                <div className="form-group">
                  <AvField
                    name="firstPassword"
                    label="New password"
                    className="form-control form-control-lg"
                    placeholder={'New password'}
                    type="password"
                    onChange={updatePassword}
                    validate={{
                      required: { value: true, errorMessage: 'Your password is required.' },
                      minLength: { value: 4, errorMessage: 'Your password is required to be at least 4 characters.' },
                      maxLength: { value: 50, errorMessage: 'Your password cannot be longer than 50 characters.' }
                    }}
                  />
                </div>
                <div className="form-group">
                  <PasswordStrengthBar password={password} />
                </div>
                <div className="form-group">
                  <AvField
                    name="secondPassword"
                    label="New password confirmation"
                    className="form-control form-control-lg"
                    placeholder="Confirm the new password"
                    type="password"
                    validate={{
                      required: { value: true, errorMessage: 'Your confirmation password is required.' },
                      minLength: { value: 4, errorMessage: 'Your confirmation password is required to be at least 4 characters.' },
                      maxLength: { value: 50, errorMessage: 'Your confirmation password cannot be longer than 50 characters.' },
                      match: { value: 'firstPassword', errorMessage: 'The password and its confirmation do not match!' }
                    }}
                  />
                </div>
                <div className="mt-3">
                  <Button id="register-submit" color="primary" className="btn-block btn-lg font-weight-medium auth-form-btn" type="submit">
                    SIGN UP
                  </Button>
                </div>
                <div className="text-center mt-4 font-weight-light">
                  Already have an account? <Link to="/login" className="text-primary">Login</Link>
                </div>
              </AvForm>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

const mapDispatchToProps = { handleRegister, reset };
type DispatchProps = typeof mapDispatchToProps;

export default connect(null, mapDispatchToProps)(RegisterPage);
