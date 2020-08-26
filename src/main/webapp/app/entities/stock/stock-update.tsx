import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './stock.reducer';
import { IStock } from 'app/shared/model/stock.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IStockUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const StockUpdate = (props: IStockUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { stockEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/stock');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...stockEntity,
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
          <h4 className="card-title" id="eAvicultureApp.batiment.home.createOrEditLabel">Create or edit a Stock</h4>
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : stockEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="stock-id">ID</Label>
                  <AvInput id="stock-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="codeStockLabel" for="stock-codeStock">
                  Code Stock
                </Label>
                <AvField id="stock-codeStock" type="text" name="codeStock" />
              </AvGroup>
              <AvGroup>
                <Label id="surfaceLabel" for="stock-surface">
                  Surface
                </Label>
                <AvField id="stock-surface" type="string" className="form-control" name="surface" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/stock" className="btn btn-light">
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
  stockEntity: storeState.stock.entity,
  loading: storeState.stock.loading,
  updating: storeState.stock.updating,
  updateSuccess: storeState.stock.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StockUpdate);
