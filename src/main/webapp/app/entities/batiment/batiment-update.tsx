import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './batiment.reducer';
import { IBatiment } from 'app/shared/model/batiment.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBatimentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export const BatimentUpdate = (props: IBatimentUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { batimentEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/batiment');
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
        ...batimentEntity,
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
      {/* <Row className="justify-content-center">
        <Col md="8">
          <h2 id="eAvicultureApp.batiment.home.createOrEditLabel">Create or edit a Batiment</h2>
        </Col>
      </Row> */}
      <div className="card">
        <div className="card-body">
          <h4 className="card-title" id="eAvicultureApp.batiment.home.createOrEditLabel">Create or edit a Batiment</h4>
          {loading ? (
            <p>Loading...</p>
          ) : (
              <AvForm model={isNew ? {} : batimentEntity} onSubmit={saveEntity} className="forms-sample">
                {!isNew ? (
                  <AvGroup>
                    <Label for="batiment-id">ID</Label>
                    <AvInput id="batiment-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeBatimentLabel" for="batiment-codeBatiment">
                    Code Batiment
                </Label>
                  <AvField id="batiment-codeBatiment" type="text" name="codeBatiment" />
                </AvGroup>
                <AvGroup>
                  <Label id="surfaceLabel" for="batiment-surface">
                    Surface
                </Label>
                  <AvField id="batiment-surface" type="string" className="form-control" name="surface" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/batiment" className="btn btn-light">
                  <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
                </Button>
              &nbsp;
                <Button color="primary" id="save-entity" type="submit" className="btn btn-primary mr-2" disabled={updating}>
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
  batimentEntity: storeState.batiment.entity,
  loading: storeState.batiment.loading,
  updating: storeState.batiment.updating,
  updateSuccess: storeState.batiment.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BatimentUpdate);
