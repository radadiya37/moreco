import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IVente, defaultValue } from 'app/shared/model/vente.model';

export const ACTION_TYPES = {
  FETCH_VENTE_LIST: 'vente/FETCH_VENTE_LIST',
  FETCH_VENTE: 'vente/FETCH_VENTE',
  CREATE_VENTE: 'vente/CREATE_VENTE',
  UPDATE_VENTE: 'vente/UPDATE_VENTE',
  DELETE_VENTE: 'vente/DELETE_VENTE',
  RESET: 'vente/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IVente>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type VenteState = Readonly<typeof initialState>;

// Reducer

export default (state: VenteState = initialState, action): VenteState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_VENTE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_VENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_VENTE):
    case REQUEST(ACTION_TYPES.UPDATE_VENTE):
    case REQUEST(ACTION_TYPES.DELETE_VENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_VENTE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_VENTE):
    case FAILURE(ACTION_TYPES.CREATE_VENTE):
    case FAILURE(ACTION_TYPES.UPDATE_VENTE):
    case FAILURE(ACTION_TYPES.DELETE_VENTE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_VENTE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_VENTE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_VENTE):
    case SUCCESS(ACTION_TYPES.UPDATE_VENTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_VENTE):
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

const apiUrl = 'api/ventes';

// Actions

export const getEntities: ICrudGetAllAction<IVente> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_VENTE_LIST,
  payload: axios.get<IVente>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IVente> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_VENTE,
    payload: axios.get<IVente>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IVente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_VENTE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IVente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_VENTE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IVente> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_VENTE,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
