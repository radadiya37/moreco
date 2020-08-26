import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LigneEclairage from './ligne-eclairage';
import LigneEclairageDetail from './ligne-eclairage-detail';
import LigneEclairageUpdate from './ligne-eclairage-update';
import LigneEclairageDeleteDialog from './ligne-eclairage-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={LigneEclairageDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LigneEclairageUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LigneEclairageUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LigneEclairageDetail} />
      <ErrorBoundaryRoute path={match.url} component={LigneEclairage} />
    </Switch>
  </>
);

export default Routes;
