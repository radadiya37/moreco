import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './depense.reducer';
import { IDepense } from 'app/shared/model/depense.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDepenseProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Depense = (props: IDepenseProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { depenseList, match, loading } = props;
  return (
    <div>
      <h2 id="depense-heading">
        Depenses
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Depense
        </Link>
      </h2>
      <div className="table-responsive">
        {depenseList && depenseList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Code Depense</th>
                <th>Quantite</th>
                <th>Date Demande</th>
                <th>Etat Depense</th>
                <th>Produit</th>
                <th>Foursnisseur</th>
                <th>Facture</th>
                <th>Phase Production</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {depenseList.map((depense, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${depense.id}`} color="link" size="sm">
                      {depense.id}
                    </Button>
                  </td>
                  <td>{depense.codeDepense}</td>
                  <td>{depense.quantite}</td>
                  <td>
                    <TextFormat type="date" value={depense.dateDemande} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{depense.etatDepense}</td>
                  <td>{depense.produit ? <Link to={`produit/${depense.produit.id}`}>{depense.produit.id}</Link> : ''}</td>
                  <td>
                    {depense.foursnisseur ? <Link to={`fournisseur/${depense.foursnisseur.id}`}>{depense.foursnisseur.id}</Link> : ''}
                  </td>
                  <td>{depense.facture ? <Link to={`facture/${depense.facture.id}`}>{depense.facture.id}</Link> : ''}</td>
                  <td>
                    {depense.phaseProduction ? (
                      <Link to={`phase-production/${depense.phaseProduction.id}`}>{depense.phaseProduction.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${depense.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${depense.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${depense.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Depenses found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ depense }: IRootState) => ({
  depenseList: depense.entities,
  loading: depense.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Depense);
