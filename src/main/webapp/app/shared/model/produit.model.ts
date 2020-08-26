import { ITypeProduit } from 'app/shared/model/type-produit.model';
import { IEmplacement } from 'app/shared/model/emplacement.model';

export interface IProduit {
  id?: number;
  codeProduit?: string;
  designation?: string;
  type?: ITypeProduit;
  emplacement?: IEmplacement;
}

export const defaultValue: Readonly<IProduit> = {};
