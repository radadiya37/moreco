import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './batiment.reducer';
import { IBatiment } from 'app/shared/model/batiment.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBatimentProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> { }

export const Batiment = (props: IBatimentProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { batimentList, match, loading } = props;
  return (
    <div className="col-lg-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2 id="batiment-heading">
            Batiments
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
              <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Batiment
        </Link>
          </h2>
          <div className="table-responsive">
            {batimentList && batimentList.length > 0 ? (
              <table className="table table-striped">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Code Batiment</th>
                    <th>Surface</th>
                    <th />
                  </tr>
                </thead>
                <tbody>
                  {batimentList.map((batiment, i) => (
                    <tr key={`entity-${i}`}>
                      <td>
                        <Button tag={Link} to={`${match.url}/${batiment.id}`} color="link" size="sm">
                          {batiment.id}
                        </Button>
                      </td>
                      <td>{batiment.codeBatiment}</td>
                      <td>{batiment.surface}</td>
                      <td className="text-right">
                        <div className="btn-group flex-btn-group-container">
                          <Button tag={Link} to={`${match.url}/${batiment.id}`} color="info" size="sm">
                            <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${batiment.id}/edit`} color="primary" size="sm">
                            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${batiment.id}/delete`} color="danger" size="sm">
                            <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                          </Button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
                </table>
              ) : (
                  !loading && <div className="alert alert-warning">No Batiments found</div>
                )}
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ batiment }: IRootState) => ({
  batimentList: batiment.entities,
  loading: batiment.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Batiment);
