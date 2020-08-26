import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILogParametreEnvironement, defaultValue } from 'app/shared/model/log-parametre-environement.model';

export const ACTION_TYPES = {
  FETCH_LOGPARAMETREENVIRONEMENT_LIST: 'logParametreEnvironement/FETCH_LOGPARAMETREENVIRONEMENT_LIST',
  FETCH_LOGPARAMETREENVIRONEMENT: 'logParametreEnvironement/FETCH_LOGPARAMETREENVIRONEMENT',
  CREATE_LOGPARAMETREENVIRONEMENT: 'logParametreEnvironement/CREATE_LOGPARAMETREENVIRONEMENT',
  UPDATE_LOGPARAMETREENVIRONEMENT: 'logParametreEnvironement/UPDATE_LOGPARAMETREENVIRONEMENT',
  DELETE_LOGPARAMETREENVIRONEMENT: 'logParametreEnvironement/DELETE_LOGPARAMETREENVIRONEMENT',
  RESET: 'logParametreEnvironement/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILogParametreEnvironement>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type LogParametreEnvironementState = Readonly<typeof initialState>;

// Reducer

export default (state: LogParametreEnvironementState = initialState, action): LogParametreEnvironementState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LOGPARAMETREENVIRONEMENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LOGPARAMETREENVIRONEMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LOGPARAMETREENVIRONEMENT):
    case REQUEST(ACTION_TYPES.UPDATE_LOGPARAMETREENVIRONEMENT):
    case REQUEST(ACTION_TYPES.DELETE_LOGPARAMETREENVIRONEMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LOGPARAMETREENVIRONEMENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LOGPARAMETREENVIRONEMENT):
    case FAILURE(ACTION_TYPES.CREATE_LOGPARAMETREENVIRONEMENT):
    case FAILURE(ACTION_TYPES.UPDATE_LOGPARAMETREENVIRONEMENT):
    case FAILURE(ACTION_TYPES.DELETE_LOGPARAMETREENVIRONEMENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOGPARAMETREENVIRONEMENT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOGPARAMETREENVIRONEMENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LOGPARAMETREENVIRONEMENT):
    case SUCCESS(ACTION_TYPES.UPDATE_LOGPARAMETREENVIRONEMENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LOGPARAMETREENVIRONEMENT):
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

const apiUrl = 'api/log-parametre-environements';

// Actions

export const getEntities: ICrudGetAllAction<ILogParametreEnvironement> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LOGPARAMETREENVIRONEMENT_LIST,
  payload: axios.get<ILogParametreEnvironement>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ILogParametreEnvironement> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LOGPARAMETREENVIRONEMENT,
    payload: axios.get<ILogParametreEnvironement>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILogParametreEnvironement> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LOGPARAMETREENVIRONEMENT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILogParametreEnvironement> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LOGPARAMETREENVIRONEMENT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILogParametreEnvironement> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LOGPARAMETREENVIRONEMENT,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
