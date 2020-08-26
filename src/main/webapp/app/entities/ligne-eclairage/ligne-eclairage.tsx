import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './ligne-eclairage.reducer';
import { ILigneEclairage } from 'app/shared/model/ligne-eclairage.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILigneEclairageProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const LigneEclairage = (props: ILigneEclairageProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { ligneEclairageList, match, loading } = props;
  return (
    <div>
      <h2 id="ligne-eclairage-heading">
        Ligne Eclairages
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Ligne Eclairage
        </Link>
      </h2>
      <div className="table-responsive">
        {ligneEclairageList && ligneEclairageList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Code Ligne</th>
                <th>Description</th>
                <th>Allume</th>
                <th>Lux Max</th>
                <th>Lux Min</th>
                <th>Lux Choisi</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {ligneEclairageList.map((ligneEclairage, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${ligneEclairage.id}`} color="link" size="sm">
                      {ligneEclairage.id}
                    </Button>
                  </td>
                  <td>{ligneEclairage.codeLigne}</td>
                  <td>{ligneEclairage.description}</td>
                  <td>{ligneEclairage.allume ? 'true' : 'false'}</td>
                  <td>{ligneEclairage.luxMax}</td>
                  <td>{ligneEclairage.luxMin}</td>
                  <td>{ligneEclairage.luxChoisi}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${ligneEclairage.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ligneEclairage.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ligneEclairage.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Ligne Eclairages found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ ligneEclairage }: IRootState) => ({
  ligneEclairageList: ligneEclairage.entities,
  loading: ligneEclairage.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LigneEclairage);
