import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './fournisseur.reducer';
import { IFournisseur } from 'app/shared/model/fournisseur.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFournisseurUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FournisseurUpdate = (props: IFournisseurUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { fournisseurEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/fournisseur');
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
        ...fournisseurEntity,
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
          <h4 className="card-title" id="eAvicultureApp.batiment.home.createOrEditLabel">Create or edit a Fournisseur</h4>
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : fournisseurEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="fournisseur-id">ID</Label>
                  <AvInput id="fournisseur-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nomCompleteLabel" for="fournisseur-nomComplete">
                  Nom Complete
                </Label>
                <AvField id="fournisseur-nomComplete" type="text" name="nomComplete" />
              </AvGroup>
              <AvGroup>
                <Label id="adresseLabel" for="fournisseur-adresse">
                  Adresse
                </Label>
                <AvField id="fournisseur-adresse" type="text" name="adresse" />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="fournisseur-email">
                  Email
                </Label>
                <AvField id="fournisseur-email" type="text" name="email" />
              </AvGroup>
              <AvGroup>
                <Label id="numeroTelLabel" for="fournisseur-numeroTel">
                  Numero Tel
                </Label>
                <AvField id="fournisseur-numeroTel" type="text" name="numeroTel" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/fournisseur" className="btn btn-light">
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
  fournisseurEntity: storeState.fournisseur.entity,
  loading: storeState.fournisseur.loading,
  updating: storeState.fournisseur.updating,
  updateSuccess: storeState.fournisseur.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FournisseurUpdate);
