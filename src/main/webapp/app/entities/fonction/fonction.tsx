import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './fonction.reducer';
import { IFonction } from 'app/shared/model/fonction.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFonctionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Fonction = (props: IFonctionProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { fonctionList, match, loading } = props;
  return (
    <div className="col-lg-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
      <h2 id="fonction-heading">
        Fonctions
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Fonction
        </Link>
      </h2>
      <div className="table-responsive">
        {fonctionList && fonctionList.length > 0 ? (
              <table className="table table-striped">
              <thead>
              <tr>
                <th>ID</th>
                <th>Code Fonction</th>
                <th>Description</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {fonctionList.map((fonction, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${fonction.id}`} color="link" size="sm">
                      {fonction.id}
                    </Button>
                  </td>
                  <td>{fonction.codeFonction}</td>
                  <td>{fonction.description}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${fonction.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${fonction.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${fonction.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          !loading && <div className="alert alert-warning">No Fonctions found</div>
        )}
      </div>
      </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ fonction }: IRootState) => ({
  fonctionList: fonction.entities,
  loading: fonction.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Fonction);
