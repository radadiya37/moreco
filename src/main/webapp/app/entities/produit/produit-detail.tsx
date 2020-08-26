import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './produit.reducer';
import { IProduit } from 'app/shared/model/produit.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProduitDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export const ProduitDetail = (props: IProduitDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { produitEntity } = props;
  return (
    <div className="col-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2>
            Produit [<b>{produitEntity.id}</b>]
        </h2>
          <AvForm>
            <div className="row">
              <div className="col-12 entity-setup-box">
                <div>
                  <AvGroup>
                    <Label id="nameLabel" for="name">
                      <span id="codeProduit">Code Produit</span>
                    </Label>
                    <input type="text" className="form-control" value={produitEntity.codeProduit} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="type" for="descr">
                      <span id="designation">Designation</span>
                    </Label>
                    <input type="text" className="form-control" value={produitEntity.designation} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="Type">Type</span>
                    </Label>
                    <input type="text" className="form-control" value={produitEntity.type ? produitEntity.type.id : ''} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="Emplacement">Emplacement</span>
                    </Label>
                    <input type="text" className="form-control" value={produitEntity.emplacement ? produitEntity.emplacement.id : ''} readOnly />
                  </AvGroup>
                </div>
                <div className="card-footer">
                  <Button tag={Link} to="/produit" replace color="info">
                    <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
                  </Button>
        &nbsp;
        <Button tag={Link} to={`/produit/${produitEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ produit }: IRootState) => ({
  produitEntity: produit.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProduitDetail);
