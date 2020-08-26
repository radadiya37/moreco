import { Moment } from 'moment';
import { ILogParametreEnvironement } from 'app/shared/model/log-parametre-environement.model';
import { IDepense } from 'app/shared/model/depense.model';
import { IBatiment } from 'app/shared/model/batiment.model';

export interface IPhaseProduction {
  id?: number;
  codePhase?: string;
  dateDebut?: Moment;
  dateFin?: Moment;
  nombrePoulets?: number;
  nombreDeces?: number;
  logParametreEnvironements?: ILogParametreEnvironement[];
  listeDepenses?: IDepense[];
  batiment?: IBatiment;
}

export const defaultValue: Readonly<IPhaseProduction> = {};
