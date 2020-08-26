import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './vente.reducer';
import { IVente } from 'app/shared/model/vente.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVenteDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const VenteDetail = (props: IVenteDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { venteEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Vente [<b>{venteEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="quantite">Quantite</span>
          </dt>
          <dd>{venteEntity.quantite}</dd>
          <dt>
            <span id="prixUnitaire">Prix Unitaire</span>
          </dt>
          <dd>{venteEntity.prixUnitaire}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{venteEntity.description}</dd>
          <dt>
            <span id="methodPaiment">Method Paiment</span>
          </dt>
          <dd>{venteEntity.methodPaiment}</dd>
          <dt>Client</dt>
          <dd>{venteEntity.client ? venteEntity.client.id : ''}</dd>
          <dt>Phase Production</dt>
          <dd>{venteEntity.phaseProduction ? venteEntity.phaseProduction.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/vente" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/vente/${venteEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ vente }: IRootState) => ({
  venteEntity: vente.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(VenteDetail);
