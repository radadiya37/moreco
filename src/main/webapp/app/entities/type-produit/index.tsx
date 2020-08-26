import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TypeProduit from './type-produit';
import TypeProduitDetail from './type-produit-detail';
import TypeProduitUpdate from './type-produit-update';
import TypeProduitDeleteDialog from './type-produit-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TypeProduitDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TypeProduitUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TypeProduitUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TypeProduitDetail} />
      <ErrorBoundaryRoute path={match.url} component={TypeProduit} />
    </Switch>
  </>
);

export default Routes;
