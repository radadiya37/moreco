import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Employe from './employe';
import Fonction from './fonction';
import Fournisseur from './fournisseur';
import Produit from './produit';
import Stock from './stock';
import Emplacement from './emplacement';
import Facture from './facture';
import PhaseProduction from './phase-production';
import Depense from './depense';
import TypeProduit from './type-produit';
import LigneEclairage from './ligne-eclairage';
import Batiment from './batiment';
import Vente from './vente';
import Client from './client';
import LogParametreEnvironement from './log-parametre-environement';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}employe`} component={Employe} />
      <ErrorBoundaryRoute path={`${match.url}fonction`} component={Fonction} />
      <ErrorBoundaryRoute path={`${match.url}fournisseur`} component={Fournisseur} />
      <ErrorBoundaryRoute path={`${match.url}produit`} component={Produit} />
      <ErrorBoundaryRoute path={`${match.url}stock`} component={Stock} />
      <ErrorBoundaryRoute path={`${match.url}emplacement`} component={Emplacement} />
      <ErrorBoundaryRoute path={`${match.url}facture`} component={Facture} />
      <ErrorBoundaryRoute path={`${match.url}phase-production`} component={PhaseProduction} />
      <ErrorBoundaryRoute path={`${match.url}depense`} component={Depense} />
      <ErrorBoundaryRoute path={`${match.url}type-produit`} component={TypeProduit} />
      <ErrorBoundaryRoute path={`${match.url}ligne-eclairage`} component={LigneEclairage} />
      <ErrorBoundaryRoute path={`${match.url}batiment`} component={Batiment} />
      <ErrorBoundaryRoute path={`${match.url}vente`} component={Vente} />
      <ErrorBoundaryRoute path={`${match.url}client`} component={Client} />
      <ErrorBoundaryRoute path={`${match.url}log-parametre-environement`} component={LogParametreEnvironement} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
