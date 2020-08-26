export interface IFournisseur {
  id?: number;
  nomComplete?: string;
  adresse?: string;
  email?: string;
  numeroTel?: string;
}

export const defaultValue: Readonly<IFournisseur> = {};
