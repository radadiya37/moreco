export interface ILigneEclairage {
  id?: number;
  codeLigne?: string;
  description?: string;
  allume?: boolean;
  luxMax?: number;
  luxMin?: number;
  luxChoisi?: number;
}

export const defaultValue: Readonly<ILigneEclairage> = {
  allume: false
};
