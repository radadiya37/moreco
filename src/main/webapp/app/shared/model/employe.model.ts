import { Moment } from 'moment';
import { IFonction } from 'app/shared/model/fonction.model';

export interface IEmploye {
  id?: number;
  prenom?: string;
  nom?: string;
  email?: string;
  numeroTel?: string;
  dateEmbauche?: Moment;
  salaire?: number;
  adresse?: string;
  cin?: string;
  dateNaissance?: Moment;
  sexe?: string;
  fonction?: IFonction;
}

export const defaultValue: Readonly<IEmploye> = {};
