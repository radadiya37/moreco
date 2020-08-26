import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILigneEclairage, defaultValue } from 'app/shared/model/ligne-eclairage.model';

export const ACTION_TYPES = {
  FETCH_LIGNEECLAIRAGE_LIST: 'ligneEclairage/FETCH_LIGNEECLAIRAGE_LIST',
  FETCH_LIGNEECLAIRAGE: 'ligneEclairage/FETCH_LIGNEECLAIRAGE',
  CREATE_LIGNEECLAIRAGE: 'ligneEclairage/CREATE_LIGNEECLAIRAGE',
  UPDATE_LIGNEECLAIRAGE: 'ligneEclairage/UPDATE_LIGNEECLAIRAGE',
  DELETE_LIGNEECLAIRAGE: 'ligneEclairage/DELETE_LIGNEECLAIRAGE',
  RESET: 'ligneEclairage/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILigneEclairage>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type LigneEclairageState = Readonly<typeof initialState>;

// Reducer

export default (state: LigneEclairageState = initialState, action): LigneEclairageState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LIGNEECLAIRAGE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LIGNEECLAIRAGE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LIGNEECLAIRAGE):
    case REQUEST(ACTION_TYPES.UPDATE_LIGNEECLAIRAGE):
    case REQUEST(ACTION_TYPES.DELETE_LIGNEECLAIRAGE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LIGNEECLAIRAGE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LIGNEECLAIRAGE):
    case FAILURE(ACTION_TYPES.CREATE_LIGNEECLAIRAGE):
    case FAILURE(ACTION_TYPES.UPDATE_LIGNEECLAIRAGE):
    case FAILURE(ACTION_TYPES.DELETE_LIGNEECLAIRAGE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LIGNEECLAIRAGE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LIGNEECLAIRAGE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LIGNEECLAIRAGE):
    case SUCCESS(ACTION_TYPES.UPDATE_LIGNEECLAIRAGE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LIGNEECLAIRAGE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/ligne-eclairages';

// Actions

export const getEntities: ICrudGetAllAction<ILigneEclairage> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LIGNEECLAIRAGE_LIST,
  payload: axios.get<ILigneEclairage>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ILigneEclairage> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LIGNEECLAIRAGE,
    payload: axios.get<ILigneEclairage>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILigneEclairage> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LIGNEECLAIRAGE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILigneEclairage> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LIGNEECLAIRAGE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILigneEclairage> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LIGNEECLAIRAGE,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
