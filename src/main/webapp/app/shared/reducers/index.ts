import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import employe, {
  EmployeState
} from 'app/entities/employe/employe.reducer';
// prettier-ignore
import fonction, {
  FonctionState
} from 'app/entities/fonction/fonction.reducer';
// prettier-ignore
import fournisseur, {
  FournisseurState
} from 'app/entities/fournisseur/fournisseur.reducer';
// prettier-ignore
import produit, {
  ProduitState
} from 'app/entities/produit/produit.reducer';
// prettier-ignore
import stock, {
  StockState
} from 'app/entities/stock/stock.reducer';
// prettier-ignore
import emplacement, {
  EmplacementState
} from 'app/entities/emplacement/emplacement.reducer';
// prettier-ignore
import facture, {
  FactureState
} from 'app/entities/facture/facture.reducer';
// prettier-ignore
import phaseProduction, {
  PhaseProductionState
} from 'app/entities/phase-production/phase-production.reducer';
// prettier-ignore
import depense, {
  DepenseState
} from 'app/entities/depense/depense.reducer';
// prettier-ignore
import typeProduit, {
  TypeProduitState
} from 'app/entities/type-produit/type-produit.reducer';
// prettier-ignore
import ligneEclairage, {
  LigneEclairageState
} from 'app/entities/ligne-eclairage/ligne-eclairage.reducer';
// prettier-ignore
import batiment, {
  BatimentState
} from 'app/entities/batiment/batiment.reducer';
// prettier-ignore
import vente, {
  VenteState
} from 'app/entities/vente/vente.reducer';
// prettier-ignore
import client, {
  ClientState
} from 'app/entities/client/client.reducer';
// prettier-ignore
import logParametreEnvironement, {
  LogParametreEnvironementState
} from 'app/entities/log-parametre-environement/log-parametre-environement.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly employe: EmployeState;
  readonly fonction: FonctionState;
  readonly fournisseur: FournisseurState;
  readonly produit: ProduitState;
  readonly stock: StockState;
  readonly emplacement: EmplacementState;
  readonly facture: FactureState;
  readonly phaseProduction: PhaseProductionState;
  readonly depense: DepenseState;
  readonly typeProduit: TypeProduitState;
  readonly ligneEclairage: LigneEclairageState;
  readonly batiment: BatimentState;
  readonly vente: VenteState;
  readonly client: ClientState;
  readonly logParametreEnvironement: LogParametreEnvironementState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  employe,
  fonction,
  fournisseur,
  produit,
  stock,
  emplacement,
  facture,
  phaseProduction,
  depense,
  typeProduit,
  ligneEclairage,
  batiment,
  vente,
  client,
  logParametreEnvironement,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
