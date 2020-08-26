import { Moment } from 'moment';
import { IProduit } from 'app/shared/model/produit.model';
import { IFournisseur } from 'app/shared/model/fournisseur.model';
import { IFacture } from 'app/shared/model/facture.model';
import { IPhaseProduction } from 'app/shared/model/phase-production.model';

export interface IDepense {
  id?: number;
  codeDepense?: string;
  quantite?: number;
  dateDemande?: Moment;
  etatDepense?: string;
  produit?: IProduit;
  foursnisseur?: IFournisseur;
  facture?: IFacture;
  phaseProduction?: IPhaseProduction;
}

export const defaultValue: Readonly<IDepense> = {};
