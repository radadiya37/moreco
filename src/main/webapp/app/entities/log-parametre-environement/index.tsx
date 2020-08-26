import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LogParametreEnvironement from './log-parametre-environement';
import LogParametreEnvironementDetail from './log-parametre-environement-detail';
import LogParametreEnvironementUpdate from './log-parametre-environement-update';
import LogParametreEnvironementDeleteDialog from './log-parametre-environement-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={LogParametreEnvironementDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LogParametreEnvironementUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LogParametreEnvironementUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LogParametreEnvironementDetail} />
      <ErrorBoundaryRoute path={match.url} component={LogParametreEnvironement} />
    </Switch>
  </>
);

export default Routes;
