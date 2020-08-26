import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './client.reducer';
import { IClient } from 'app/shared/model/client.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IClientUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ClientUpdate = (props: IClientUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { clientEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/client');
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
        ...clientEntity,
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
          <h2 id="eAvicultureApp.client.home.createOrEditLabel">Create or edit a Client</h2>
        </Col>
      </Row> */}
      <div className="card">
        <div className="card-body">
          <h4 className="card-title" id="eAvicultureApp.client.home.createOrEditLabel">Create or edit a Client</h4>
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : clientEntity} onSubmit={saveEntity} className="forms-sample">
              {!isNew ? (
                <AvGroup>
                  <Label for="client-id">ID</Label>
                  <AvInput id="client-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nomCompletLabel" for="client-nomComplet">
                  Nom Complet
                </Label>
                <AvField id="client-nomComplet" type="text" name="nomComplet" />
              </AvGroup>
              <AvGroup>
                <Label id="numeroTelLabel" for="client-numeroTel">
                  Numero Tel
                </Label>
                <AvField id="client-numeroTel" type="text" name="numeroTel" />
              </AvGroup>
              <AvGroup>
                <Label id="adresseLabel" for="client-adresse">
                  Adresse
                </Label>
                <AvField id="client-adresse" type="text" name="adresse" />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="client-email">
                  Email
                </Label>
                <AvField id="client-email" type="text" name="email" />
              </AvGroup>
              <AvGroup>
                <Label id="numeroCompteBancaireLabel" for="client-numeroCompteBancaire">
                  Numero Compte Bancaire
                </Label>
                <AvField id="client-numeroCompteBancaire" type="text" name="numeroCompteBancaire" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/client" className="btn btn-light">
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
  clientEntity: storeState.client.entity,
  loading: storeState.client.loading,
  updating: storeState.client.updating,
  updateSuccess: storeState.client.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ClientUpdate);
