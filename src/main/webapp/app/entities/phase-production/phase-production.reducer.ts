import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPhaseProduction, defaultValue } from 'app/shared/model/phase-production.model';

export const ACTION_TYPES = {
  FETCH_PHASEPRODUCTION_LIST: 'phaseProduction/FETCH_PHASEPRODUCTION_LIST',
  FETCH_PHASEPRODUCTION: 'phaseProduction/FETCH_PHASEPRODUCTION',
  CREATE_PHASEPRODUCTION: 'phaseProduction/CREATE_PHASEPRODUCTION',
  UPDATE_PHASEPRODUCTION: 'phaseProduction/UPDATE_PHASEPRODUCTION',
  DELETE_PHASEPRODUCTION: 'phaseProduction/DELETE_PHASEPRODUCTION',
  RESET: 'phaseProduction/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPhaseProduction>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PhaseProductionState = Readonly<typeof initialState>;

// Reducer

export default (state: PhaseProductionState = initialState, action): PhaseProductionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PHASEPRODUCTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PHASEPRODUCTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PHASEPRODUCTION):
    case REQUEST(ACTION_TYPES.UPDATE_PHASEPRODUCTION):
    case REQUEST(ACTION_TYPES.DELETE_PHASEPRODUCTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PHASEPRODUCTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PHASEPRODUCTION):
    case FAILURE(ACTION_TYPES.CREATE_PHASEPRODUCTION):
    case FAILURE(ACTION_TYPES.UPDATE_PHASEPRODUCTION):
    case FAILURE(ACTION_TYPES.DELETE_PHASEPRODUCTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PHASEPRODUCTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PHASEPRODUCTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PHASEPRODUCTION):
    case SUCCESS(ACTION_TYPES.UPDATE_PHASEPRODUCTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PHASEPRODUCTION):
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

const apiUrl = 'api/phase-productions';

// Actions

export const getEntities: ICrudGetAllAction<IPhaseProduction> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PHASEPRODUCTION_LIST,
  payload: axios.get<IPhaseProduction>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPhaseProduction> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PHASEPRODUCTION,
    payload: axios.get<IPhaseProduction>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPhaseProduction> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PHASEPRODUCTION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPhaseProduction> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PHASEPRODUCTION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPhaseProduction> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PHASEPRODUCTION,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
