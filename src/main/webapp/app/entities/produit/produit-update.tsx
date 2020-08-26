import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITypeProduit } from 'app/shared/model/type-produit.model';
import { getEntities as getTypeProduits } from 'app/entities/type-produit/type-produit.reducer';
import { IEmplacement } from 'app/shared/model/emplacement.model';
import { getEntities as getEmplacements } from 'app/entities/emplacement/emplacement.reducer';
import { getEntity, updateEntity, createEntity, reset } from './produit.reducer';
import { IProduit } from 'app/shared/model/produit.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProduitUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export const ProduitUpdate = (props: IProduitUpdateProps) => {
  const [typeId, setTypeId] = useState('0');
  const [emplacementId, setEmplacementId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { produitEntity, typeProduits, emplacements, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/produit');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTypeProduits();
    props.getEmplacements();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...produitEntity,
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
          <h4 className="card-title" id="eAvicultureApp.batiment.home.createOrEditLabel">Create or edit a Produit</h4>
          {loading ? (
            <p>Loading...</p>
          ) : (
              <AvForm model={isNew ? {} : produitEntity} onSubmit={saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="produit-id">ID</Label>
                    <AvInput id="produit-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeProduitLabel" for="produit-codeProduit">
                    Code Produit
                </Label>
                  <AvField id="produit-codeProduit" type="text" name="codeProduit" />
                </AvGroup>
                <AvGroup>
                  <Label id="designationLabel" for="produit-designation">
                    Designation
                </Label>
                  <AvField id="produit-designation" type="text" name="designation" />
                </AvGroup>
                <AvGroup>
                  <Label for="produit-type">Type</Label>
                  <AvInput id="produit-type" type="select" className="form-control" name="type.id">
                    <option value="" key="0" />
                    {typeProduits
                      ? typeProduits.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="produit-emplacement">Emplacement</Label>
                  <AvInput id="produit-emplacement" type="select" className="form-control" name="emplacement.id">
                    <option value="" key="0" />
                    {emplacements
                      ? emplacements.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/produit" className="btn btn-light">
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
  typeProduits: storeState.typeProduit.entities,
  emplacements: storeState.emplacement.entities,
  produitEntity: storeState.produit.entity,
  loading: storeState.produit.loading,
  updating: storeState.produit.updating,
  updateSuccess: storeState.produit.updateSuccess
});

const mapDispatchToProps = {
  getTypeProduits,
  getEmplacements,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProduitUpdate);
