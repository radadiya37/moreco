import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './fournisseur.reducer';
import { IFournisseur } from 'app/shared/model/fournisseur.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFournisseurProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> { }

export const Fournisseur = (props: IFournisseurProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { fournisseurList, match, loading } = props;
  return (
    <div className="col-lg-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2 id="fournisseur-heading">
            Fournisseurs
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
              <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Fournisseur
        </Link>
          </h2>
          <div className="table-responsive">
            {fournisseurList && fournisseurList.length > 0 ? (
              <table className="table table-striped">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Nom Complete</th>
                    <th>Adresse</th>
                    <th>Email</th>
                    <th>Numero Tel</th>
                    <th />
                  </tr>
                </thead>
                <tbody>
                  {fournisseurList.map((fournisseur, i) => (
                    <tr key={`entity-${i}`}>
                      <td>
                        <Button tag={Link} to={`${match.url}/${fournisseur.id}`} color="link" size="sm">
                          {fournisseur.id}
                        </Button>
                      </td>
                      <td>{fournisseur.nomComplete}</td>
                      <td>{fournisseur.adresse}</td>
                      <td>{fournisseur.email}</td>
                      <td>{fournisseur.numeroTel}</td>
                      <td className="text-right">
                        <div className="btn-group flex-btn-group-container">
                          <Button tag={Link} to={`${match.url}/${fournisseur.id}`} color="info" size="sm">
                            <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${fournisseur.id}/edit`} color="primary" size="sm">
                            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${fournisseur.id}/delete`} color="danger" size="sm">
                            <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                          </Button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            ) : (
                !loading && <div className="alert alert-warning">No Fournisseurs found</div>
              )}
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ fournisseur }: IRootState) => ({
  fournisseurList: fournisseur.entities,
  loading: fournisseur.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Fournisseur);
