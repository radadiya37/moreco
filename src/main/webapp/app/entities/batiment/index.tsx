import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Batiment from './batiment';
import BatimentDetail from './batiment-detail';
import BatimentUpdate from './batiment-update';
import BatimentDeleteDialog from './batiment-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BatimentDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BatimentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BatimentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BatimentDetail} />
      <ErrorBoundaryRoute path={match.url} component={Batiment} />
    </Switch>
  </>
);

export default Routes;
