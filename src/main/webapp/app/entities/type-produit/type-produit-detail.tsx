import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './type-produit.reducer';
import { ITypeProduit } from 'app/shared/model/type-produit.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITypeProduitDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TypeProduitDetail = (props: ITypeProduitDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { typeProduitEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          TypeProduit [<b>{typeProduitEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="codeTypeProduit">Code Type Produit</span>
          </dt>
          <dd>{typeProduitEntity.codeTypeProduit}</dd>
          <dt>
            <span id="designation">Designation</span>
          </dt>
          <dd>{typeProduitEntity.designation}</dd>
        </dl>
        <Button tag={Link} to="/type-produit" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/type-produit/${typeProduitEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ typeProduit }: IRootState) => ({
  typeProduitEntity: typeProduit.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TypeProduitDetail);
