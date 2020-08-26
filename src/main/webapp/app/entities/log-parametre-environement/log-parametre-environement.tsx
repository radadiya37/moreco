import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './log-parametre-environement.reducer';
import { ILogParametreEnvironement } from 'app/shared/model/log-parametre-environement.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILogParametreEnvironementProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const LogParametreEnvironement = (props: ILogParametreEnvironementProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { logParametreEnvironementList, match, loading } = props;
  return (
    <div>
      <h2 id="log-parametre-environement-heading">
        Log Parametre Environements
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Log Parametre Environement
        </Link>
      </h2>
      <div className="table-responsive">
        {logParametreEnvironementList && logParametreEnvironementList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Temperature</th>
                <th>Humidite</th>
                <th>Date Log</th>
                <th>Phase Production</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {logParametreEnvironementList.map((logParametreEnvironement, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${logParametreEnvironement.id}`} color="link" size="sm">
                      {logParametreEnvironement.id}
                    </Button>
                  </td>
                  <td>{logParametreEnvironement.temperature}</td>
                  <td>{logParametreEnvironement.humidite}</td>
                  <td>
                    <TextFormat type="date" value={logParametreEnvironement.dateLog} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    {logParametreEnvironement.phaseProduction ? (
                      <Link to={`phase-production/${logParametreEnvironement.phaseProduction.id}`}>
                        {logParametreEnvironement.phaseProduction.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${logParametreEnvironement.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${logParametreEnvironement.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${logParametreEnvironement.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Log Parametre Environements found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ logParametreEnvironement }: IRootState) => ({
  logParametreEnvironementList: logParametreEnvironement.entities,
  loading: logParametreEnvironement.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LogParametreEnvironement);
