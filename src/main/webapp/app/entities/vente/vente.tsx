import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './vente.reducer';
import { IVente } from 'app/shared/model/vente.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVenteProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Vente = (props: IVenteProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { venteList, match, loading } = props;
  return (
    <div>
      <h2 id="vente-heading">
        Ventes
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Vente
        </Link>
      </h2>
      <div className="table-responsive">
        {venteList && venteList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Quantite</th>
                <th>Prix Unitaire</th>
                <th>Description</th>
                <th>Method Paiment</th>
                <th>Client</th>
                <th>Phase Production</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {venteList.map((vente, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${vente.id}`} color="link" size="sm">
                      {vente.id}
                    </Button>
                  </td>
                  <td>{vente.quantite}</td>
                  <td>{vente.prixUnitaire}</td>
                  <td>{vente.description}</td>
                  <td>{vente.methodPaiment}</td>
                  <td>{vente.client ? <Link to={`client/${vente.client.id}`}>{vente.client.id}</Link> : ''}</td>
                  <td>
                    {vente.phaseProduction ? (
                      <Link to={`phase-production/${vente.phaseProduction.id}`}>{vente.phaseProduction.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${vente.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${vente.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${vente.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Ventes found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ vente }: IRootState) => ({
  venteList: vente.entities,
  loading: vente.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Vente);
