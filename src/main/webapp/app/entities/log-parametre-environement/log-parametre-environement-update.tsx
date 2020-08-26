import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPhaseProduction } from 'app/shared/model/phase-production.model';
import { getEntities as getPhaseProductions } from 'app/entities/phase-production/phase-production.reducer';
import { getEntity, updateEntity, createEntity, reset } from './log-parametre-environement.reducer';
import { ILogParametreEnvironement } from 'app/shared/model/log-parametre-environement.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILogParametreEnvironementUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LogParametreEnvironementUpdate = (props: ILogParametreEnvironementUpdateProps) => {
  const [phaseProductionId, setPhaseProductionId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { logParametreEnvironementEntity, phaseProductions, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/log-parametre-environement');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

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
        ...logParametreEnvironementEntity,
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
          <h2 id="eAvicultureApp.logParametreEnvironement.home.createOrEditLabel">Create or edit a LogParametreEnvironement</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : logParametreEnvironementEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="log-parametre-environement-id">ID</Label>
                  <AvInput id="log-parametre-environement-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="temperatureLabel" for="log-parametre-environement-temperature">
                  Temperature
                </Label>
                <AvField id="log-parametre-environement-temperature" type="string" className="form-control" name="temperature" />
              </AvGroup>
              <AvGroup>
                <Label id="humiditeLabel" for="log-parametre-environement-humidite">
                  Humidite
                </Label>
                <AvField id="log-parametre-environement-humidite" type="string" className="form-control" name="humidite" />
              </AvGroup>
              <AvGroup>
                <Label id="dateLogLabel" for="log-parametre-environement-dateLog">
                  Date Log
                </Label>
                <AvField id="log-parametre-environement-dateLog" type="date" className="form-control" name="dateLog" />
              </AvGroup>
              <AvGroup>
                <Label for="log-parametre-environement-phaseProduction">Phase Production</Label>
                <AvInput id="log-parametre-environement-phaseProduction" type="select" className="form-control" name="phaseProduction.id">
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
              <Button tag={Link} id="cancel-save" to="/log-parametre-environement" replace color="info">
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
  phaseProductions: storeState.phaseProduction.entities,
  logParametreEnvironementEntity: storeState.logParametreEnvironement.entity,
  loading: storeState.logParametreEnvironement.loading,
  updating: storeState.logParametreEnvironement.updating,
  updateSuccess: storeState.logParametreEnvironement.updateSuccess
});

const mapDispatchToProps = {
  getPhaseProductions,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LogParametreEnvironementUpdate);
