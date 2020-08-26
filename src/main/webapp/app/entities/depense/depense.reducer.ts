import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDepense, defaultValue } from 'app/shared/model/depense.model';

export const ACTION_TYPES = {
  FETCH_DEPENSE_LIST: 'depense/FETCH_DEPENSE_LIST',
  FETCH_DEPENSE: 'depense/FETCH_DEPENSE',
  CREATE_DEPENSE: 'depense/CREATE_DEPENSE',
  UPDATE_DEPENSE: 'depense/UPDATE_DEPENSE',
  DELETE_DEPENSE: 'depense/DELETE_DEPENSE',
  RESET: 'depense/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDepense>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DepenseState = Readonly<typeof initialState>;

// Reducer

export default (state: DepenseState = initialState, action): DepenseState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DEPENSE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DEPENSE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DEPENSE):
    case REQUEST(ACTION_TYPES.UPDATE_DEPENSE):
    case REQUEST(ACTION_TYPES.DELETE_DEPENSE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DEPENSE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DEPENSE):
    case FAILURE(ACTION_TYPES.CREATE_DEPENSE):
    case FAILURE(ACTION_TYPES.UPDATE_DEPENSE):
    case FAILURE(ACTION_TYPES.DELETE_DEPENSE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEPENSE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEPENSE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DEPENSE):
    case SUCCESS(ACTION_TYPES.UPDATE_DEPENSE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DEPENSE):
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

const apiUrl = 'api/depenses';

// Actions

export const getEntities: ICrudGetAllAction<IDepense> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DEPENSE_LIST,
  payload: axios.get<IDepense>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDepense> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DEPENSE,
    payload: axios.get<IDepense>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDepense> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DEPENSE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDepense> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DEPENSE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDepense> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DEPENSE,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
