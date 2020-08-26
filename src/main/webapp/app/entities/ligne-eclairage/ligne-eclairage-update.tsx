import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './ligne-eclairage.reducer';
import { ILigneEclairage } from 'app/shared/model/ligne-eclairage.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILigneEclairageUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LigneEclairageUpdate = (props: ILigneEclairageUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { ligneEclairageEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ligne-eclairage');
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
        ...ligneEclairageEntity,
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
          <h2 id="eAvicultureApp.ligneEclairage.home.createOrEditLabel">Create or edit a LigneEclairage</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : ligneEclairageEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ligne-eclairage-id">ID</Label>
                  <AvInput id="ligne-eclairage-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="codeLigneLabel" for="ligne-eclairage-codeLigne">
                  Code Ligne
                </Label>
                <AvField id="ligne-eclairage-codeLigne" type="text" name="codeLigne" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="ligne-eclairage-description">
                  Description
                </Label>
                <AvField id="ligne-eclairage-description" type="text" name="description" />
              </AvGroup>
              <AvGroup check>
                <Label id="allumeLabel">
                  <AvInput id="ligne-eclairage-allume" type="checkbox" className="form-check-input" name="allume" />
                  Allume
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="luxMaxLabel" for="ligne-eclairage-luxMax">
                  Lux Max
                </Label>
                <AvField id="ligne-eclairage-luxMax" type="string" className="form-control" name="luxMax" />
              </AvGroup>
              <AvGroup>
                <Label id="luxMinLabel" for="ligne-eclairage-luxMin">
                  Lux Min
                </Label>
                <AvField id="ligne-eclairage-luxMin" type="string" className="form-control" name="luxMin" />
              </AvGroup>
              <AvGroup>
                <Label id="luxChoisiLabel" for="ligne-eclairage-luxChoisi">
                  Lux Choisi
                </Label>
                <AvField id="ligne-eclairage-luxChoisi" type="string" className="form-control" name="luxChoisi" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/ligne-eclairage" replace color="info">
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
  ligneEclairageEntity: storeState.ligneEclairage.entity,
  loading: storeState.ligneEclairage.loading,
  updating: storeState.ligneEclairage.updating,
  updateSuccess: storeState.ligneEclairage.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LigneEclairageUpdate);
