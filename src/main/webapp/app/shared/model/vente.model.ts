import { IClient } from 'app/shared/model/client.model';
import { IPhaseProduction } from 'app/shared/model/phase-production.model';

export interface IVente {
  id?: number;
  quantite?: number;
  prixUnitaire?: number;
  description?: string;
  methodPaiment?: string;
  client?: IClient;
  phaseProduction?: IPhaseProduction;
}

export const defaultValue: Readonly<IVente> = {};
