import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './phase-production.reducer';
import { IPhaseProduction } from 'app/shared/model/phase-production.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPhaseProductionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> { }

export const PhaseProduction = (props: IPhaseProductionProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { phaseProductionList, match, loading } = props;
  return (
    <div className="col-lg-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2 id="phase-production-heading">
            Phase Productions
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
              <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Phase Production
        </Link>
          </h2>
          <div className="table-responsive">
            {phaseProductionList && phaseProductionList.length > 0 ? (
              <table className="table table-striped">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Code Phase</th>
                    <th>Date Debut</th>
                    <th>Date Fin</th>
                    <th>Nombre Poulets</th>
                    <th>Nombre Deces</th>
                    <th>Batiment</th>
                    <th />
                  </tr>
                </thead>
                <tbody>
                  {phaseProductionList.map((phaseProduction, i) => (
                    <tr key={`entity-${i}`}>
                      <td>
                        <Button tag={Link} to={`${match.url}/${phaseProduction.id}`} color="link" size="sm">
                          {phaseProduction.id}
                        </Button>
                      </td>
                      <td>{phaseProduction.codePhase}</td>
                      <td>
                        <TextFormat type="date" value={phaseProduction.dateDebut} format={APP_LOCAL_DATE_FORMAT} />
                      </td>
                      <td>
                        <TextFormat type="date" value={phaseProduction.dateFin} format={APP_LOCAL_DATE_FORMAT} />
                      </td>
                      <td>{phaseProduction.nombrePoulets}</td>
                      <td>{phaseProduction.nombreDeces}</td>
                      <td>
                        {phaseProduction.batiment ? (
                          <Link to={`batiment/${phaseProduction.batiment.id}`}>{phaseProduction.batiment.id}</Link>
                        ) : (
                            ''
                          )}
                      </td>
                      <td className="text-right">
                        <div className="btn-group flex-btn-group-container">
                          <Button tag={Link} to={`${match.url}/${phaseProduction.id}`} color="info" size="sm">
                            <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${phaseProduction.id}/edit`} color="primary" size="sm">
                            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${phaseProduction.id}/delete`} color="danger" size="sm">
                            <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                          </Button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            ) : (
                !loading && <div className="alert alert-warning">No Phase Productions found</div>
              )}
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ phaseProduction }: IRootState) => ({
  phaseProductionList: phaseProduction.entities,
  loading: phaseProduction.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PhaseProduction);
