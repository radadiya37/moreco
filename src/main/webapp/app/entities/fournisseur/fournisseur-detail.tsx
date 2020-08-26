import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './fournisseur.reducer';
import { IFournisseur } from 'app/shared/model/fournisseur.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFournisseurDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export const FournisseurDetail = (props: IFournisseurDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { fournisseurEntity } = props;
  return (
    <div className="col-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2>
            Fournisseur [<b>{fournisseurEntity.id}</b>]
        </h2>
          <AvForm>
            <div className="row">
              <div className="col-12 entity-setup-box">
                <div>
                  <AvGroup>
                    <Label id="nameLabel" for="name">
                      <span id="nomComplete">Nom Complete</span>
                    </Label>
                    <input type="text" className="form-control" value={fournisseurEntity.nomComplete} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="adresse">Adresse</span>
                    </Label>
                    <input type="text" className="form-control" value={fournisseurEntity.adresse} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="email">Email</span>
                    </Label>
                    <input type="text" className="form-control" value={fournisseurEntity.email} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="numeroTel">Numero Tel</span>
                    </Label>
                    <input type="text" className="form-control" value={fournisseurEntity.numeroTel} readOnly />
                  </AvGroup>
                </div>
                <div className="card-footer">
                  <Button tag={Link} to="/fournisseur" replace color="info">
                    <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
                  </Button>
        &nbsp;
        <Button tag={Link} to={`/fournisseur/${fournisseurEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ fournisseur }: IRootState) => ({
  fournisseurEntity: fournisseur.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FournisseurDetail);
