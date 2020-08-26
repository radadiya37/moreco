import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IStock } from 'app/shared/model/stock.model';
import { getEntities as getStocks } from 'app/entities/stock/stock.reducer';
import { getEntity, updateEntity, createEntity, reset } from './emplacement.reducer';
import { IEmplacement } from 'app/shared/model/emplacement.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEmplacementUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export const EmplacementUpdate = (props: IEmplacementUpdateProps) => {
  const [stockId, setStockId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { emplacementEntity, stocks, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/emplacement');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getStocks();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...emplacementEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div className="col-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h4 className="card-title" id="eAvicultureApp.client.home.createOrEditLabel">Create or edit a Emplacement</h4>
          {loading ? (
            <p>Loading...</p>
          ) : (
              <AvForm model={isNew ? {} : emplacementEntity} onSubmit={saveEntity} className="forms-sample">
                {!isNew ? (
                  <AvGroup>
                    <Label for="emplacement-id">ID</Label>
                    <AvInput id="emplacement-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeEmplacementLabel" for="emplacement-codeEmplacement">
                    Code Emplacement
                </Label>
                  <AvField id="emplacement-codeEmplacement" type="text" name="codeEmplacement" />
                </AvGroup>
                <AvGroup>
                  <Label id="volumeLabel" for="emplacement-volume">
                    Volume
                </Label>
                  <AvField id="emplacement-volume" type="string" className="form-control" name="volume" />
                </AvGroup>
                <AvGroup check>
                  <Label id="reserveLabel">
                    <AvInput id="emplacement-reserve" type="checkbox" className="form-check-input" name="reserve" />
                  Reserve
                </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="emplacement-stock">Stock</Label>
                  <AvInput id="emplacement-stock" type="select" className="form-control" name="stock.id">
                    <option value="" key="0" />
                    {stocks
                      ? stocks.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/emplacement" className="btn btn-light">
                  <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
                </Button>
              &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating} className="btn btn-primary mr-2">
                  <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
              </AvForm>
            )}
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  stocks: storeState.stock.entities,
  emplacementEntity: storeState.emplacement.entity,
  loading: storeState.emplacement.loading,
  updating: storeState.emplacement.updating,
  updateSuccess: storeState.emplacement.updateSuccess
});

const mapDispatchToProps = {
  getStocks,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EmplacementUpdate);
