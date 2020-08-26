import './home.scss';

import React from 'react';
import { Link, Redirect } from 'react-router-dom';

import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';

import { IRootState } from 'app/shared/reducers';

export type IHomeProp = StateProps;

export const Home = (props: IHomeProp) => {
  const { account } = props;

  return (
    <Row>
      <Col md="9">
        {account && account.login ? (
          <div>
            <div>
              <Alert color="success">You are logged in as user {account.login}.</Alert>
            </div>

            <p>If you have any question on JHipster:</p>

            <ul>
              <li>
                <a href="https://www.jhipster.tech/" target="_blank" rel="noopener noreferrer">
                  JHipster homepage
            </a>
              </li>
              <li>
                <a href="http://stackoverflow.com/tags/jhipster/info" target="_blank" rel="noopener noreferrer">
                  JHipster on Stack Overflow
            </a>
              </li>
              <li>
                <a href="https://github.com/jhipster/generator-jhipster/issues?state=open" target="_blank" rel="noopener noreferrer">
                  JHipster bug tracker
            </a>
              </li>
              <li>
                <a href="https://gitter.im/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer">
                  JHipster public chat room
            </a>
              </li>
              <li>
                <a href="https://twitter.com/jhipster" target="_blank" rel="noopener noreferrer">
                  follow @jhipster on Twitter
            </a>
              </li>
            </ul>

            <p>
              If you like JHipster, do not forget to give us a star on{' '}
              <a href="https://github.com/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer">
                Github
          </a>
          !
        </p>
          </div>
        ) : (
            <Redirect to={'/login'} />
          )}
      </Col>
    </Row>
  );
};

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated
});

type StateProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps)(Home);
