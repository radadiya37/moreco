import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITypeProduit, defaultValue } from 'app/shared/model/type-produit.model';

export const ACTION_TYPES = {
  FETCH_TYPEPRODUIT_LIST: 'typeProduit/FETCH_TYPEPRODUIT_LIST',
  FETCH_TYPEPRODUIT: 'typeProduit/FETCH_TYPEPRODUIT',
  CREATE_TYPEPRODUIT: 'typeProduit/CREATE_TYPEPRODUIT',
  UPDATE_TYPEPRODUIT: 'typeProduit/UPDATE_TYPEPRODUIT',
  DELETE_TYPEPRODUIT: 'typeProduit/DELETE_TYPEPRODUIT',
  RESET: 'typeProduit/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITypeProduit>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TypeProduitState = Readonly<typeof initialState>;

// Reducer

export default (state: TypeProduitState = initialState, action): TypeProduitState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TYPEPRODUIT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TYPEPRODUIT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TYPEPRODUIT):
    case REQUEST(ACTION_TYPES.UPDATE_TYPEPRODUIT):
    case REQUEST(ACTION_TYPES.DELETE_TYPEPRODUIT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TYPEPRODUIT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TYPEPRODUIT):
    case FAILURE(ACTION_TYPES.CREATE_TYPEPRODUIT):
    case FAILURE(ACTION_TYPES.UPDATE_TYPEPRODUIT):
    case FAILURE(ACTION_TYPES.DELETE_TYPEPRODUIT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TYPEPRODUIT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TYPEPRODUIT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TYPEPRODUIT):
    case SUCCESS(ACTION_TYPES.UPDATE_TYPEPRODUIT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TYPEPRODUIT):
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

const apiUrl = 'api/type-produits';

// Actions

export const getEntities: ICrudGetAllAction<ITypeProduit> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TYPEPRODUIT_LIST,
  payload: axios.get<ITypeProduit>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITypeProduit> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TYPEPRODUIT,
    payload: axios.get<ITypeProduit>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITypeProduit> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TYPEPRODUIT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITypeProduit> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TYPEPRODUIT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITypeProduit> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TYPEPRODUIT,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
