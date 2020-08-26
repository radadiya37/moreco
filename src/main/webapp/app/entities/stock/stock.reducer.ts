import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IStock, defaultValue } from 'app/shared/model/stock.model';

export const ACTION_TYPES = {
  FETCH_STOCK_LIST: 'stock/FETCH_STOCK_LIST',
  FETCH_STOCK: 'stock/FETCH_STOCK',
  CREATE_STOCK: 'stock/CREATE_STOCK',
  UPDATE_STOCK: 'stock/UPDATE_STOCK',
  DELETE_STOCK: 'stock/DELETE_STOCK',
  RESET: 'stock/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IStock>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type StockState = Readonly<typeof initialState>;

// Reducer

export default (state: StockState = initialState, action): StockState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_STOCK_LIST):
    case REQUEST(ACTION_TYPES.FETCH_STOCK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_STOCK):
    case REQUEST(ACTION_TYPES.UPDATE_STOCK):
    case REQUEST(ACTION_TYPES.DELETE_STOCK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_STOCK_LIST):
    case FAILURE(ACTION_TYPES.FETCH_STOCK):
    case FAILURE(ACTION_TYPES.CREATE_STOCK):
    case FAILURE(ACTION_TYPES.UPDATE_STOCK):
    case FAILURE(ACTION_TYPES.DELETE_STOCK):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_STOCK_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STOCK):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_STOCK):
    case SUCCESS(ACTION_TYPES.UPDATE_STOCK):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_STOCK):
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

const apiUrl = 'api/stocks';

// Actions

export const getEntities: ICrudGetAllAction<IStock> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_STOCK_LIST,
  payload: axios.get<IStock>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IStock> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_STOCK,
    payload: axios.get<IStock>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IStock> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_STOCK,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IStock> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_STOCK,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IStock> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_STOCK,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
