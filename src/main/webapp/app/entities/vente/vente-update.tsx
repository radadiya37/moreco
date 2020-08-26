import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IClient } from 'app/shared/model/client.model';
import { getEntities as getClients } from 'app/entities/client/client.reducer';
import { IPhaseProduction } from 'app/shared/model/phase-production.model';
import { getEntities as getPhaseProductions } from 'app/entities/phase-production/phase-production.reducer';
import { getEntity, updateEntity, createEntity, reset } from './vente.reducer';
import { IVente } from 'app/shared/model/vente.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IVenteUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const VenteUpdate = (props: IVenteUpdateProps) => {
  const [clientId, setClientId] = useState('0');
  const [phaseProductionId, setPhaseProductionId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { venteEntity, clients, phaseProductions, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/vente');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getClients();
    props.getPhaseProductions();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...venteEntity,
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
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="eAvicultureApp.vente.home.createOrEditLabel">Create or edit a Vente</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : venteEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="vente-id">ID</Label>
                  <AvInput id="vente-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="quantiteLabel" for="vente-quantite">
                  Quantite
                </Label>
                <AvField id="vente-quantite" type="string" className="form-control" name="quantite" />
              </AvGroup>
              <AvGroup>
                <Label id="prixUnitaireLabel" for="vente-prixUnitaire">
                  Prix Unitaire
                </Label>
                <AvField id="vente-prixUnitaire" type="text" name="prixUnitaire" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="vente-description">
                  Description
                </Label>
                <AvField id="vente-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="methodPaimentLabel" for="vente-methodPaiment">
                  Method Paiment
                </Label>
                <AvField id="vente-methodPaiment" type="text" name="methodPaiment" />
              </AvGroup>
              <AvGroup>
                <Label for="vente-client">Client</Label>
                <AvInput id="vente-client" type="select" className="form-control" name="client.id">
                  <option value="" key="0" />
                  {clients
                    ? clients.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="vente-phaseProduction">Phase Production</Label>
                <AvInput id="vente-phaseProduction" type="select" className="form-control" name="phaseProduction.id">
                  <option value="" key="0" />
                  {phaseProductions
                    ? phaseProductions.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/vente" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  clients: storeState.client.entities,
  phaseProductions: storeState.phaseProduction.entities,
  venteEntity: storeState.vente.entity,
  loading: storeState.vente.loading,
  updating: storeState.vente.updating,
  updateSuccess: storeState.vente.updateSuccess
});

const mapDispatchToProps = {
  getClients,
  getPhaseProductions,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(VenteUpdate);
