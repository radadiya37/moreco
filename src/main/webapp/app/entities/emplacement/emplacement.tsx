import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './emplacement.reducer';
import { IEmplacement } from 'app/shared/model/emplacement.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmplacementProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> { }

export const Emplacement = (props: IEmplacementProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { emplacementList, match, loading } = props;
  return (
    <div className="col-lg-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2 id="emplacement-heading">
            Emplacements
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
              <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Emplacement
        </Link>
          </h2>
          <div className="table-responsive">
            {emplacementList && emplacementList.length > 0 ? (
              <table className="table table-striped">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Code Emplacement</th>
                    <th>Volume</th>
                    <th>Reserve</th>
                    <th>Stock</th>
                    <th />
                  </tr>
                </thead>
                <tbody>
                  {emplacementList.map((emplacement, i) => (
                    <tr key={`entity-${i}`}>
                      <td>
                        <Button tag={Link} to={`${match.url}/${emplacement.id}`} color="link" size="sm">
                          {emplacement.id}
                        </Button>
                      </td>
                      <td>{emplacement.codeEmplacement}</td>
                      <td>{emplacement.volume}</td>
                      <td>{emplacement.reserve ? 'true' : 'false'}</td>
                      <td>{emplacement.stock ? <Link to={`stock/${emplacement.stock.id}`}>{emplacement.stock.id}</Link> : ''}</td>
                      <td className="text-right">
                        <div className="btn-group flex-btn-group-container">
                          <Button tag={Link} to={`${match.url}/${emplacement.id}`} color="info" size="sm">
                            <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${emplacement.id}/edit`} color="primary" size="sm">
                            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${emplacement.id}/delete`} color="danger" size="sm">
                            <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                          </Button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            ) : (
                !loading && <div className="alert alert-warning">No Emplacements found</div>
              )}
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ emplacement }: IRootState) => ({
  emplacementList: emplacement.entities,
  loading: emplacement.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Emplacement);
