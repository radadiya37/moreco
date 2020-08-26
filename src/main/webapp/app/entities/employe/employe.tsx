import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './employe.reducer';
import { IEmploye } from 'app/shared/model/employe.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmployeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> { }

export const Employe = (props: IEmployeProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { employeList, match, loading } = props;
  return (
    <div className="col-lg-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2 id="employe-heading">
            Employes
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
              <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Employe
        </Link>
          </h2>
          <div className="table-responsive">
            {employeList && employeList.length > 0 ? (
              <table className="table table-striped">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Prenom</th>
                    <th>Nom</th>
                    <th>Email</th>
                    <th>Numero Tel</th>
                    <th>Date Embauche</th>
                    <th>Salaire</th>
                    <th>Adresse</th>
                    <th>Cin</th>
                    <th>Date Naissance</th>
                    <th>Sexe</th>
                    <th>Fonction</th>
                    <th />
                  </tr>
                </thead>
                <tbody>
                  {employeList.map((employe, i) => (
                    <tr key={`entity-${i}`}>
                      <td>
                        <Button tag={Link} to={`${match.url}/${employe.id}`} color="link" size="sm">
                          {employe.id}
                        </Button>
                      </td>
                      <td>{employe.prenom}</td>
                      <td>{employe.nom}</td>
                      <td>{employe.email}</td>
                      <td>{employe.numeroTel}</td>
                      <td>
                        <TextFormat type="date" value={employe.dateEmbauche} format={APP_LOCAL_DATE_FORMAT} />
                      </td>
                      <td>{employe.salaire}</td>
                      <td>{employe.adresse}</td>
                      <td>{employe.cin}</td>
                      <td>
                        <TextFormat type="date" value={employe.dateNaissance} format={APP_LOCAL_DATE_FORMAT} />
                      </td>
                      <td>{employe.sexe}</td>
                      <td>{employe.fonction ? <Link to={`fonction/${employe.fonction.id}`}>{employe.fonction.id}</Link> : ''}</td>
                      <td className="text-right">
                        <div className="btn-group flex-btn-group-container">
                          <Button tag={Link} to={`${match.url}/${employe.id}`} color="info" size="sm">
                            <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${employe.id}/edit`} color="primary" size="sm">
                            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                          </Button>
                          <Button tag={Link} to={`${match.url}/${employe.id}/delete`} color="danger" size="sm">
                            <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                          </Button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            ) : (
                !loading && <div className="alert alert-warning">No Employes found</div>
              )}
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ employe }: IRootState) => ({
  employeList: employe.entities,
  loading: employe.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Employe);
