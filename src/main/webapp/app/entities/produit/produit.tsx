import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './produit.reducer';
import { IProduit } from 'app/shared/model/produit.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProduitProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> { }

export const Produit = (props: IProduitProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { produitList, match, loading } = props;
  return (
    <div className="col-lg-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2 id="produit-heading">
            Produits
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
              <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Produit
        </Link>
          </h2>
          <div className="table-responsive">
            {produitList && produitList.length > 0 ? (
              <table className="table table-striped">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Code Produit</th>
                    <th>Designation</th>
                    <th>Type</th>
                    <th>Emplacement</th>
                    <th />
                  </tr>
                </thead>
                <tbody>
                  {produitList.map((produit, i) => (
                    <tr key={`entity-${i}`}>
                      <td>
                        <Button tag={Link} to={`${match.url}/${produit.id}`} color="link" size="sm">
                          {produit.id}
                        </Button>
                      </td>
                      <td>{produit.codeProduit}</td>
                      <td>{produit.designation}</td>
                      <td>{produit.type ? <Link to={`type-produit/${produit.type.id}`}>{produit.type.id}</Link> : ''}</td>
                      <td>{produit.emplacement ? <Link to={`emplacement/${produit.emplacement.id}`}>{produit.emplacement.id}</Link> : ''}</td>
                      <td className="text-right">
                        <div className="btn-group flex-btn-group-container">
                          <Button tag={Link} to={`${match.url}/${produit.id}`} color="info" size="sm">
                            <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${produit.id}/edit`} color="primary" size="sm">
                            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${produit.id}/delete`} color="danger" size="sm">
                            <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                          </Button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            ) : (
                !loading && <div className="alert alert-warning">No Produits found</div>
              )}
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ produit }: IRootState) => ({
  produitList: produit.entities,
  loading: produit.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Produit);
