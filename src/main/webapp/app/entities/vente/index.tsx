import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Vente from './vente';
import VenteDetail from './vente-detail';
import VenteUpdate from './vente-update';
import VenteDeleteDialog from './vente-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={VenteDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={VenteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={VenteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={VenteDetail} />
      <ErrorBoundaryRoute path={match.url} component={Vente} />
    </Switch>
  </>
);

export default Routes;
