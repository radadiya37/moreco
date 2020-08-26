import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './phase-production.reducer';
import { IPhaseProduction } from 'app/shared/model/phase-production.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPhaseProductionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export const PhaseProductionDetail = (props: IPhaseProductionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { phaseProductionEntity } = props;
  return (
    <div className="col-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2>
            PhaseProduction [<b>{phaseProductionEntity.id}</b>]
        </h2>
          <AvForm>
            <div className="row">
              <div className="col-12 entity-setup-box">
                <div>
                  <AvGroup>
                    <Label id="nameLabel" for="name">
                      <span id="codePhase">Code Phase</span>
                    </Label>
                    <input type="text" className="form-control" value={phaseProductionEntity.codePhase} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="dateDebut">Date Debut</span>
                    </Label>
                    <AvField id="phase-production-dateDebut" type="date" className="form-control" name="dateDebut" value={phaseProductionEntity.dateDebut} disabled/>
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="dateFin">Date Fin</span>
                    </Label>
                    <AvField id="phase-production-dateFin" type="date" className="form-control" name="dateFin" value={phaseProductionEntity.dateFin} disabled/>
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="nombrePoulets">Nombre Poulets</span>
                    </Label>
                    <input type="text" className="form-control" value={phaseProductionEntity.nombrePoulets} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="nombreDeces">Nombre Deces</span>
                    </Label>
                    <input type="text" className="form-control" value={phaseProductionEntity.nombreDeces} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="batiment">Batiment</span>
                    </Label>
                    <input type="text" className="form-control" value={phaseProductionEntity.batiment ? phaseProductionEntity.batiment.id : ''} readOnly />
                  </AvGroup>

                </div>
                <div className="card-footer">
                  <Button tag={Link} to="/phase-production" replace color="info">
                    <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
                  </Button>
        &nbsp;
        <Button tag={Link} to={`/phase-production/${phaseProductionEntity.id}/edit`} replace color="primary">
                    <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                  </Button>
                </div>
              </div>
            </div>
          </AvForm>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ phaseProduction }: IRootState) => ({
  phaseProductionEntity: phaseProduction.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PhaseProductionDetail);
