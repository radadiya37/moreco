import { IStock } from 'app/shared/model/stock.model';

export interface IEmplacement {
  id?: number;
  codeEmplacement?: string;
  volume?: number;
  reserve?: boolean;
  stock?: IStock;
}

export const defaultValue: Readonly<IEmplacement> = {
  reserve: false
};
