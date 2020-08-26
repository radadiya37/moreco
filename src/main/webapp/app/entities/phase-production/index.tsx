import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PhaseProduction from './phase-production';
import PhaseProductionDetail from './phase-production-detail';
import PhaseProductionUpdate from './phase-production-update';
import PhaseProductionDeleteDialog from './phase-production-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PhaseProductionDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PhaseProductionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PhaseProductionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PhaseProductionDetail} />
      <ErrorBoundaryRoute path={match.url} component={PhaseProduction} />
    </Switch>
  </>
);

export default Routes;
