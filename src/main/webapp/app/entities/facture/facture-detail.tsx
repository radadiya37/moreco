import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './facture.reducer';
import { IFacture } from 'app/shared/model/facture.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFactureDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export const FactureDetail = (props: IFactureDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { factureEntity } = props;
  return (
    <div className="col-12 grid-margin stretch-card">
      <div className="card">
        <div className="card-body">
          <h2>
            Facture [<b>{factureEntity.id}</b>]
        </h2>
          <AvForm>
            <div className="row">
              <div className="col-12 entity-setup-box">
                <div>
                  <AvGroup>
                    <Label id="nameLabel" for="name">
                      <span id="numeroFacture">Numero Facture</span>
                    </Label>
                    <input type="text" className="form-control" value={factureEntity.numeroFacture} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="dateFacturation">Date Facturation</span>
                    </Label>
                    <TextFormat value={factureEntity.dateFacturation} type="date" format={APP_LOCAL_DATE_FORMAT} />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="prixUnit">Prix Unit</span>
                    </Label>
                    <input type="text" className="form-control" value={factureEntity.prixUnit} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="tva">Tva</span>
                    </Label>
                    <input type="text" className="form-control" value={factureEntity.tva} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="fraisLivraison">Frais Livraison</span>
                    </Label>
                    <input type="text" className="form-control" value={factureEntity.fraisLivraison} readOnly />
                  </AvGroup>
                  <AvGroup>
                    <Label id="descrLabel" for="descr">
                      <span id="methodPaiment">Method Paiment</span>
                    </Label>
                    <input type="text" className="form-control" value={factureEntity.methodPaiment} readOnly />
                  </AvGroup>

                </div>
                <div className="card-footer">
                  <Button tag={Link} to="/facture" replace color="info">
                    <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
                  </Button>
        &nbsp;
        <Button tag={Link} to={`/facture/${factureEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ facture }: IRootState) => ({
  factureEntity: facture.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FactureDetail);
