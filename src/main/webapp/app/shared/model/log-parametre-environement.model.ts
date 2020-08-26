import { Moment } from 'moment';
import { IPhaseProduction } from 'app/shared/model/phase-production.model';

export interface ILogParametreEnvironement {
  id?: number;
  temperature?: number;
  humidite?: number;
  dateLog?: Moment;
  phaseProduction?: IPhaseProduction;
}

export const defaultValue: Readonly<ILogParametreEnvironement> = {};
