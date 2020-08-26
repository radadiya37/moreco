import { Moment } from 'moment';
import { IDepense } from 'app/shared/model/depense.model';

export interface IFacture {
  id?: number;
  numeroFacture?: string;
  dateFacturation?: Moment;
  prixUnit?: number;
  tva?: number;
  fraisLivraison?: number;
  methodPaiment?: string;
  depenses?: IDepense[];
}

export const defaultValue: Readonly<IFacture> = {};
