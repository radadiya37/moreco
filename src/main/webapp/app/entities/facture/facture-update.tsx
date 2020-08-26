import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './facture.reducer';
import { IFacture } from 'app/shared/model/facture.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { ButtonGroup } from 'react-bootstrap';

export interface IFactureUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export const FactureUpdate = (props: IFactureUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { factureEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/facture');
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
        ...factureEntity,
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
          <h4 className="card-title">Create or edit a Facture</h4>
          {loading ? (
            <p>Loading...</p>
          ) : (
              <AvForm model={isNew ? {} : factureEntity} onSubmit={saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="facture-id">ID</Label>
                    <AvInput id="facture-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="numeroFactureLabel" for="facture-numeroFacture">
                    Numero Facture
                </Label>
                  <AvField id="facture-numeroFacture" type="text" name="numeroFacture" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateFacturationLabel" for="facture-dateFacturation">
                    Date Facturation
                </Label>
                  <AvField id="facture-dateFacturation" type="date" className="form-control" name="dateFacturation" />
                </AvGroup>
                <AvGroup>
                  <Label id="prixUnitLabel" for="facture-prixUnit">
                    Prix Unit
                </Label>
                  <AvField id="facture-prixUnit" type="text" name="prixUnit" />
                </AvGroup>
                <AvGroup>
                  <Label id="tvaLabel" for="facture-tva">
                    Tva
                </Label>
                  <AvField id="facture-tva" type="string" className="form-control" name="tva" />
                </AvGroup>
                <AvGroup>
                  <Label id="fraisLivraisonLabel" for="facture-fraisLivraison">
                    Frais Livraison
                </Label>
                  <AvField id="facture-fraisLivraison" type="string" className="form-control" name="fraisLivraison" />
                </AvGroup>
                <AvGroup>
                  <Label id="methodPaimentLabel" for="facture-methodPaiment">
                    Method Paiment
                </Label>
                  <AvField id="facture-methodPaiment" type="text" name="methodPaiment" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/facture" className="btn btn-light">
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
  factureEntity: storeState.facture.entity,
  loading: storeState.facture.loading,
  updating: storeState.facture.updating,
  updateSuccess: storeState.facture.updateSuccess,
  factureList: storeState.facture.entities,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FactureUpdate);
