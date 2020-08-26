export interface IStock {
  id?: number;
  codeStock?: string;
  surface?: number;
}

export const defaultValue: Readonly<IStock> = {};
