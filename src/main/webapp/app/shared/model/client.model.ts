export interface IClient {
  id?: number;
  nomComplet?: string;
  numeroTel?: string;
  adresse?: string;
  email?: string;
  numeroCompteBancaire?: string;
}

export const defaultValue: Readonly<IClient> = {};
