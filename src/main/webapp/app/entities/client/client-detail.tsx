import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './client.reducer';
import { IClient } from 'app/shared/model/client.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IClientDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export const ClientDetail = (props: IClientDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { clientEntity } = props;
  return (
    <div className="col-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2 className="card-title">
            Client [<b>{clientEntity.id}</b>]
        </h2>
          <AvForm>
            <div className="row">
              <div className="col-12 entity-setup-box">
                <div>
                  <AvGroup>
                    <Label id="nameLabel" for="name">
                      <span id="nomComplet">Nom Complet</span>
                    </Label>
                    <input type="text" className="form-control" value={clientEntity.id} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="numeroTel">Numero Tel</span>
                    </Label>
                    <input type="text" className="form-control" value={clientEntity.numeroTel} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="addressLabel" for="address">
                      <span id="adresse">Adresse</span>
                    </Label>
                    <input type="text" className="form-control" value={clientEntity.adresse} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="cityLabel" for="city">
                      <span id="email">Email</span>
                    </Label>
                    <input type="text" className="form-control" value={clientEntity.email} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="stateLabel" for="state">
                      <dd>{clientEntity.numeroCompteBancaire}</dd>
                    </Label>
                    <input type="text" className="form-control" value={clientEntity.numeroCompteBancaire} readOnly />
                  </AvGroup>
                </div>
                <div className="card-footer">
                  <Button tag={Link} to="/client" replace color="info">
                    <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
                  </Button>
        &nbsp;
        <Button tag={Link} to={`/client/${clientEntity.id}/edit`} replace color="primary">
                    <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                  </Button>
                </div>
              </div>
            </div>
          </AvForm>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ client }: IRootState) => ({
  clientEntity: client.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ClientDetail);
